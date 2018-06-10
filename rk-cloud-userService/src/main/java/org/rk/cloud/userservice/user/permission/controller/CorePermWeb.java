package org.rk.cloud.userservice.user.permission.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.permission.service.ICorePermService;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rk/web/core/user/perm/")
public class CorePermWeb extends BaseController<CorePerm> {
	@Resource(name = "CorePermService")
	private ICorePermService corePermService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryList(int start, int length, int draw, String permName, String permCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permName", "%" + permName + "%");
		params.put("permCode", "%" + permCode + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = corePermService.selectModelListPage(params, page);
		return page;
	}

	@RequestMapping(value = "queryByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryByCode(String code) {
		CorePerm corePerm = corePermService.selectByCode(code);
		return corePerm;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CorePerm corePerm = corePermService.selectModel(id);
		return corePerm;
	}

	public ICorePermService getCorePermService() {
		return corePermService;
	}

}
