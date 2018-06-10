/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.module.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.module.service.ICoreModuleService;
import org.rk.core.common.bean.PageData;
import org.rk.core.domain.module.CoreModule;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月28日
 */
@Controller
@RequestMapping("/rk/web/core/domain/module/")
public class CoreModuleWeb extends BaseController<CoreModule> {
	@Resource(name = "CoreModuleService")
	private ICoreModuleService coreModuleService;

	// @RequiresPermissions(value="coreModuleWeb:queryList")
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(Map<String, Object> params) {
		List<CoreModule> result = coreModuleService.selectModelList(params);
		return result;
	}

	@RequiresPermissions(value = "coreModuleWeb:queryPage")
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryList(int start, int length, int draw, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%" + name + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreModuleService.selectModelListPage(params, page);
		return page;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(String code) {
		CoreModule coreModule = coreModuleService.selectByCode(code);
		return coreModule;
	}

	public ICoreModuleService getCoreModuleService() {
		return coreModuleService;
	}

}
