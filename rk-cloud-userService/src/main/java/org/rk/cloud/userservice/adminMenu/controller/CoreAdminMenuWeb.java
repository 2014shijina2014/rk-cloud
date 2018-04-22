/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.adminMenu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.domain.service.ICoreAdminMenuService;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月28日
 */
@Controller
@RequestMapping(value = "/rk/web/core/domain/adminMenu/")
public class CoreAdminMenuWeb extends BaseController<CoreAdminMenu> {
	@Resource(name = "CoreAdminMenuService")
	private ICoreAdminMenuService coreAdminMenuService;

	@RequestMapping(value = "queryTree", method = RequestMethod.POST)
	public @ResponseBody Object queryTree(Map<String, Object> params) {
		List<CoreAdminMenu> menuTree = coreAdminMenuService.selectModelList(params);
		return menuTree;
	}

	@RequestMapping(value = "queryMenu", method = RequestMethod.POST)
	public @ResponseBody Object queryMenu(String moduleCode,String fatherCode) {
		List<CoreAdminMenu> menuList = coreAdminMenuService.selectMenu(moduleCode,fatherCode);
		return menuList;
	}

	@RequestMapping(value = "queryMenuByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryMenuByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<CoreAdminMenu> adminMenu = coreAdminMenuService.selectModelList(params);
		if (RkObjectUtil.isEmpty(adminMenu)) {
			return ajaxSucc("没有找到数据");
		}
		return adminMenu.get(0);
	}

	/**
	 * @return the coreAdminMenuService
	 */
	public ICoreAdminMenuService getCoreAdminMenuService() {
		return coreAdminMenuService;
	}

	
}
