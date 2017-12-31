package org.rk.cloud.userservice.unit.unitAdmin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.unit.unitAdmin.CoreUnitAdmin;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rk/admin/core/unit/unitAdmin/")
public class CoreUnitAdminAdmin extends CoreUnitAdminWeb {
	@RequiresPermissions(value="coreUnitAdmin:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUnitAdmin coreUnitAdmin) {
		if(RkObjectUtil.isEmpty(coreUnitAdmin.getId())){
			coreUnitAdmin.setCreator(SecurityUtil.getUserName());
			getCoreUnitAdminService().insertModel(coreUnitAdmin);
		}else{
			coreUnitAdmin.setUpdator(SecurityUtil.getUserName());
			getCoreUnitAdminService().updateModel(coreUnitAdmin);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="coreUnitAdmin:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUnitAdminService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
