/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.infokey.controller;

import java.util.List;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月10日
 */
@Controller
@RequestMapping("/rk/admin/core/user/userInfoKey/")
public class CoreUserInfokeyAdmin extends CoreUserInfokeyWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserInfokey coreUserInfokey) {
		if(RkObjectUtil.isEmpty(coreUserInfokey.getId())){
			coreUserInfokey.setCreator(SecurityUtil.getUserName());
			getCoreUserInfokeyService().insertModel(coreUserInfokey);
		}else{
			coreUserInfokey.setUpdator(SecurityUtil.getUserName());
			getCoreUserInfokeyService().updateModel(coreUserInfokey);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserInfokeyService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
