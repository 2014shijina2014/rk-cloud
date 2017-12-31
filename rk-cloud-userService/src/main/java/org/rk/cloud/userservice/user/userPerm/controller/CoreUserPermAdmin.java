package org.rk.cloud.userservice.user.userPerm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.userPerm.CoreUserPerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rk/admin/core/user/userPerm/")
public class CoreUserPermAdmin extends CoreUserPermWeb {
	@RequiresPermissions(value="coreUserPerm:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserPerm coreUserPerm) {
		if(RkObjectUtil.isEmpty(coreUserPerm.getId())){
			coreUserPerm.setCreator(SecurityUtil.getUserName());
			getCoreUserPermService().insertModel(coreUserPerm);
		}else{
			coreUserPerm.setUpdator(SecurityUtil.getUserName());
			getCoreUserPermService().updateModel(coreUserPerm);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="coreUserPerm:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserPermService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
