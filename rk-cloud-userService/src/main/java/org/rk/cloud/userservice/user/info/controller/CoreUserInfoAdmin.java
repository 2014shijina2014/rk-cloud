/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.info.controller;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.user.info.CoreUserInfo;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.user.CoreUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月11日
 */
@Controller
@RequestMapping("/rk/admin/core/user/userInfo/")
public class CoreUserInfoAdmin extends CoreUserInfoWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(@RequestParam()Map<String,Object> params) {
		CoreUser coreUser=new CoreUser();
		Long userId=null;
		if(!RkObjectUtil.isEmpty(params.get("id"))){
			userId=Long.parseLong((String)params.get("id"));
		}
		coreUser.setId(userId);
		coreUser.setEmail((String)params.get("email"));
		coreUser.setMobilephone((String)params.get("mobilephone"));
		coreUser.setUserName((String)params.get("userName"));
		if(RkObjectUtil.isEmpty(coreUser.getId())){
			coreUser.setCreator(SecurityUtil.getUserName());
			coreUser.setUserType(RkConst.userType.domainAdmin);//用户类型，默认站点用户
			userId=getCoreUserService().registerUser(coreUser);
		}else{
			coreUser.setUpdator(SecurityUtil.getUserName());
			getCoreUserService().updateModel(coreUser);
			userId=coreUser.getId();
		}
		//保存扩展信息
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key =entry.getKey();
			String value=(String) entry.getValue();
			if(key.equals("id")||key.equals("userName")||key.equals("email")||key.equals("mobilephone")||key.equals("userType")||key.equals("isActive")){
				continue;
			}
			CoreUserInfo coreUserInfo=new CoreUserInfo();
			coreUserInfo.setUserId(userId);
			coreUserInfo.setKeyCode(key);
			coreUserInfo.setInfoValue(value);
			coreUserInfo.setCreator(SecurityUtil.getUserName());
			getCoreUserInfoService().updateUserInfo(coreUserInfo);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserInfoService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
