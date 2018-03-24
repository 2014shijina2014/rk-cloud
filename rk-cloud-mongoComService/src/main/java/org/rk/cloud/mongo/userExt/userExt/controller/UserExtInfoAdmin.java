/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:07:55
 */
package org.rk.cloud.mongo.userExt.userExt.controller;

import org.rk.cloud.mongo.userExt.userExt.UserExtInfo;
import org.rk.core.common.util.RKDateUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：
 */
@Controller
@RequestMapping("/rk/admin/mongo/userExt/userExtInfo/")
public class UserExtInfoAdmin extends UserExtInfoWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(UserExtInfo userExtInfo,JSONObject fieldInfoToJson) {
		if(RkObjectUtil.isEmpty(userExtInfo)) {
			userExtInfo = new UserExtInfo();
		}
		userExtInfo.setCreateTime(RKDateUtil.getCurrenDate().getTime());
		userExtInfo.setUserExtInfoJson(fieldInfoToJson);
		if(RkObjectUtil.isEmpty(userExtInfo.getMenberNumber())){
			ajaxError("menberNumber不可为空");
		}
		getUserExtInfoService().insert(userExtInfo);
		return ajaxSucc("保存成功");
	}
}
