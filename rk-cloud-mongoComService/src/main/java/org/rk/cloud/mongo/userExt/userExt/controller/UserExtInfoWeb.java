/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:05:35
 */
package org.rk.cloud.mongo.userExt.userExt.controller;

import javax.annotation.Resource;

import org.rk.cloud.mongo.userExt.userExt.UserExtInfo;
import org.rk.cloud.mongo.userExt.userExt.service.IUserExtInfoService;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * @描述：
 */
@Controller
@RequestMapping("/rk/web/mongo/userExt/userExtInfo/")
public class UserExtInfoWeb extends BaseController<UserExtInfo>{
	@Resource(name = "userExtInfoService")
	private IUserExtInfoService userExtInfoService;
	
	public IUserExtInfoService getUserExtInfoService() {
		return userExtInfoService;
	}

	public void setUserExtInfoService(IUserExtInfoService userExtInfoService) {
		this.userExtInfoService = userExtInfoService;
	}

	@RequestMapping(value = "queryByMenbernNum", method = RequestMethod.POST)
	public @ResponseBody Object queryByMenbernNum(String menberNum) {
		return getUserExtInfoService().findByMenbernNum(menberNum);
	}
}
