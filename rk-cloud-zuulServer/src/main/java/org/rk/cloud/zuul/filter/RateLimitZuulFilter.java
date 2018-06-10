/**
 * 
 */
package org.rk.cloud.zuul.filter;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.JsonMsg;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.LoadPropSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author 曹仁道（cavion）
 * @email caorendao187@163.com
 * @描述：限流过滤器 2018年1月7日 下午10:44:17
 */
@Component
public class RateLimitZuulFilter extends ZuulFilter {
	private static final String SERVICE_ID_KEY = "serviceId";
	private Map<String, RateLimiter> map = Maps.newConcurrentMap();
	@Autowired
	private Environment env;

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	@Override
	public Object run() {
		String goalRateLimit =LoadPropSourceUtil.getValueForDefault("zull.goalRateLimit", "10");
		try {
			RequestContext context = RequestContext.getCurrentContext();
			HttpServletRequest request = context.getRequest();
			HttpSession session = request.getSession();
			SecurityUtil.getUser();
			Object obj = session.getAttribute("session");
			session.setAttribute("username", "testsession");
			HttpServletResponse response = context.getResponse();
			String key = null;
			// 对于service格式的路由
			String serviceId = (String) context.get(SERVICE_ID_KEY);
			if (serviceId != null) {
				key = serviceId;
				map.putIfAbsent(serviceId, RateLimiter.create(Double.parseDouble(goalRateLimit)));
			}else {
				// 对于URL格式的路由
				URL routeHost = context.getRouteHost();
				if (routeHost != null) {
					String url = routeHost.toString();
					key = url;
					map.putIfAbsent(url, RateLimiter.create(Double.parseDouble(goalRateLimit)));
				}
			}
			RateLimiter rateLimiter = map.get(key);
			if (!rateLimiter.tryAcquire()) {
				HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
				JsonMsg jsonmsg = new JsonMsg();
				jsonmsg.setStatusCode(String.valueOf(httpStatus.value()));
				jsonmsg.setMsg("命中流控，请求拒绝");
				
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.setStatus(httpStatus.value());
				response.getWriter().append(JSON.toJSONString(jsonmsg));
				System.out.println("==========="+httpStatus.getReasonPhrase());
				context.setResponse(response);
				context.setSendZuulResponse(false);
				//throw new ZuulException(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
			}
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// 限流开关控制器
		String isOpenFilter = env.getProperty("zull.isOpenFilter", "1");
		if (RkConst.yesno.yes.equals(isOpenFilter)) {
			return true;
		}
		return false;
	}

	@Override
	public int filterOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
