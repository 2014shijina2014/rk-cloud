/**
 * 
 */
package org.rk.cloud.zuul.filter;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.rk.core.common.constant.RkConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author 曹仁道（cavion）
 * @email caorendao187@163.com
 * @描述：限流过滤器 2018年1月7日 下午10:44:17
 */
public class RateLimitZuulFilter extends ZuulFilter {
	private static final String STR_NUM_1 = "1";
	private static final String SERVICE_ID_KEY = "";
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
		/*try {
			RequestContext context = RequestContext.getCurrentContext();
			HttpServletResponse response = context.getResponse();
			String key = null;
			// 对于service格式的路由，走RibbonRoutingFilter
			String serviceId = (String) context.get(SERVICE_ID_KEY);
			if (serviceId != null) {
				key = serviceId;
				map.putIfAbsent(serviceId, RateLimiter.create(10.0));
			}else {
				// 对于URL格式的路由，走SimpleHostRoutingFilter
				URL routeHost = context.getRouteHost();
				if (routeHost != null) {
					String url = routeHost.toString();
					key = url;
					map.putIfAbsent(url, RateLimiter.create(20.0));
				}
			}
			RateLimiter rateLimiter = map.get(key);
			if (!rateLimiter.tryAcquire()) {
				HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
				response.setContentType(MediaType.TEXT_PLAIN_VALUE);
				response.setStatus(httpStatus.value());
				response.getWriter().append(httpStatus.getReasonPhrase());
				context.setSendZuulResponse(false);
				throw new ZuulException(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
			}
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}*/
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// 限流开关控制器
		String isOpenFilter = env.getProperty("zull.isOpenFilter", "1");
		if (STR_NUM_1.equals(isOpenFilter) || RkConst.yesno.yes.equals(isOpenFilter)) {
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
