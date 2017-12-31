package org.rk.cloud.userservice.unit.unitAdminPerm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.cloud.userservice.unit.unitAdminPerm.service.ICoreUnitAdminPermService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rk/web/core/unit/unitAdminPerm/")
public class CoreUnitAdminPermWeb extends BaseController<CoreUnitAdminPerm> {
	@Resource(name = "CoreUnitAdminPermService")
	private ICoreUnitAdminPermService coreUnitAdminPermService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUnitAdminPermService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CoreUnitAdminPerm> resultList = coreUnitAdminPermService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUnitAdminPerm coreUnitAdminPerm = coreUnitAdminPermService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreUnitAdminPerm)) {
			return ajaxError("没有找到数据");
		}
		return coreUnitAdminPerm;
	}
	public ICoreUnitAdminPermService getCoreUnitAdminPermService() {
		return coreUnitAdminPermService;
	}

}
