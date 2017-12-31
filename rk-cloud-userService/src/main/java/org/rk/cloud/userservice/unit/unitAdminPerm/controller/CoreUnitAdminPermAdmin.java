package org.rk.cloud.userservice.unit.unitAdminPerm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rk/admin/core/unit/unitAdminPerm/")
public class CoreUnitAdminPermAdmin extends CoreUnitAdminPermWeb {
	@RequiresPermissions(value="coreUnitAdminPerm:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUnitAdminPerm coreUnitAdminPerm) {
		if(RkObjectUtil.isEmpty(coreUnitAdminPerm.getId())){
			coreUnitAdminPerm.setCreator(SecurityUtil.getUserName());
			getCoreUnitAdminPermService().insertModel(coreUnitAdminPerm);
		}else{
			coreUnitAdminPerm.setUpdator(SecurityUtil.getUserName());
			getCoreUnitAdminPermService().updateModel(coreUnitAdminPerm);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="coreUnitAdminPerm:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUnitAdminPermService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
