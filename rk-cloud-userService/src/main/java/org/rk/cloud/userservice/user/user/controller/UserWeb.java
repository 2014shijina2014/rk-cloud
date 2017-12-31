/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.service.ICoreUserService;
import org.rk.core.user.user.CoreUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/web/core/user/user/")
public class UserWeb extends BaseController<CoreUser> {
	@Resource(name = "CoreUserService")
	private ICoreUserService coreUserService;

	public @ResponseBody Object createUser(CoreUser coreUser) {
		coreUserService.registerUser(coreUser);
		// 注册成功，发送激活邮件
		
		return ajaxSucc("注册成功");
	}
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public @ResponseBody Object userRegister(CoreUser coreUser) {
		coreUser.setUserType(RkConst.userType.user);//这是普通用户
		return createUser(coreUser);
	}

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "%" + userName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUserService.selectModelListPage(params, page);
		return page;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(String userName, String email) {
		CoreUser coreUser = coreUserService.selectUserByUserName(userName);
		if (RkObjectUtil.isEmpty(coreUser)) {
			coreUser = coreUserService.selectUserByEmail(email);
		}
		return coreUser;
	}
	@RequestMapping(value = "queryCurr", method = RequestMethod.POST)
	public @ResponseBody Object queryCurr() {
		Long userId=SecurityUtil.getUserId();
		CoreUser coreUser = coreUserService.selectModel(userId);
		if (RkObjectUtil.isEmpty(coreUser)) {
			return ajaxError("没有找到账户或您没登录");
		}
		return coreUser;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Object login(String username, String password, String verifyCode,HttpServletRequest request) {
		String loginResultStr=coreUserService.login(username, password);
		return ajaxSucc(loginResultStr);
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("logout")
	public @ResponseBody Object logout() {
		SecurityUtil.getSubject().logout();
		return ajaxSucc("退出成功！");
	}
	public ICoreUserService getCoreUserService() {
		return coreUserService;
	}
	
}
