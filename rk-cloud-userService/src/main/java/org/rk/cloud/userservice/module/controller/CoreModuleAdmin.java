/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.module.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.domain.module.CoreModule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/domain/module/")
public class CoreModuleAdmin extends CoreModuleWeb {
	@RequiresPermissions(value = "coreModuleAdmin:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreModule coreModule) {
		if (RkObjectUtil.isEmpty(coreModule.getId())) {
			coreModule.setCreator(SecurityUtil.getUserName());
			getCoreModuleService().insertModel(coreModule);
		} else {
			coreModule.setUpdator(SecurityUtil.getUserName());
			getCoreModuleService().updateModel(coreModule);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value = "coreModuleAdmin:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreModuleService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
