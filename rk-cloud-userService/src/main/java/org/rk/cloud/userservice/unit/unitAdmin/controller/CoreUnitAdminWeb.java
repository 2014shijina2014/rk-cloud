package org.rk.cloud.userservice.unit.unitAdmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdmin.CoreUnitAdmin;
import org.rk.cloud.userservice.unit.unitAdmin.service.ICoreUnitAdminService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rk/web/core/unit/unitAdmin/")
public class CoreUnitAdminWeb extends BaseController<CoreUnitAdmin> {
	@Resource(name = "CoreUnitAdminService")
	private ICoreUnitAdminService coreUnitAdminService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw,String unitName,String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitName", "%"+unitName+"%");
		params.put("userName", "%"+userName+"%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUnitAdminService.selectListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> resultList = coreUnitAdminService.selectList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUnitAdmin coreUnitAdmin = coreUnitAdminService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreUnitAdmin)) {
			return ajaxError("没有找到数据");
		}
		return coreUnitAdmin;
	}
	public ICoreUnitAdminService getCoreUnitAdminService() {
		return coreUnitAdminService;
	}

}
