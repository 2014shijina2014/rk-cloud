/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.adminMenu.controller;

import java.util.List;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * 2017年1月28日
 */
@Controller
@RequestMapping(value = "/rk/admin/core/domain/adminMenu/")
public class CoreAdminMenuAdmin extends CoreAdminMenuWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreAdminMenu coreAdminMenu) {
		if(RkObjectUtil.isEmpty(coreAdminMenu.getId())){
			coreAdminMenu.setCreator(SecurityUtil.getUserName());
			getCoreAdminMenuService().insertTreeModel(coreAdminMenu);
		}else{
			coreAdminMenu.setUpdator(SecurityUtil.getUserName());
			getCoreAdminMenuService().updateModel(coreAdminMenu);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreAdminMenuService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}

}
