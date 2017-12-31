/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.info.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.info.CoreUserInfo;
import org.rk.cloud.userservice.user.info.service.ICoreUserInfoService;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.service.ICoreUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月11日
 */
@Controller
@RequestMapping("/rk/web/core/user/userInfo/")
public class CoreUserInfoWeb extends BaseController<CoreUserInfo> {
	@Resource(name = "CoreUserInfoService")
	private ICoreUserInfoService coreUserInfoService;
	@Resource(name = "CoreUserService")
	private ICoreUserService coreUserService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "%" + userName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUserInfoService.selectListPage(params, page);
		return page;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long userId) {
		Map<String,Object> userInfo = coreUserInfoService.selectUserInfo(userId);
		return userInfo;
	}
	@RequestMapping(value = "queryUser", method = RequestMethod.POST)
	public @ResponseBody Object queryUser() {
		if(RkObjectUtil.isEmpty(SecurityUtil.getUserId())){
			RKAlert.Error("您没有登陆，请先登陆");
		}
		Map<String,Object> userInfo = coreUserInfoService.selectUserInfo(SecurityUtil.getUserId());
		return userInfo;
	}

	public ICoreUserInfoService getCoreUserInfoService() {
		return coreUserInfoService;
	}

	public ICoreUserService getCoreUserService() {
		return coreUserService;
	}
	
}
