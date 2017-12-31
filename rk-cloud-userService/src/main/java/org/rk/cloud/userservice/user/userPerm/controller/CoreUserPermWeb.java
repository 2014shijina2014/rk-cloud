package org.rk.cloud.userservice.user.userPerm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.service.ICoreUserPermService;
import org.rk.core.user.userPerm.CoreUserPerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rk/web/core/user/userPerm/")
public class CoreUserPermWeb extends BaseController<CoreUserPerm> {
	@Resource(name = "CoreUserPermService")
	private ICoreUserPermService coreUserPermService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUserPermService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CoreUserPerm> resultList = coreUserPermService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUserPerm coreUserPerm = coreUserPermService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreUserPerm)) {
			return ajaxError("没有找到数据");
		}
		return coreUserPerm;
	}
	public ICoreUserPermService getCoreUserPermService() {
		return coreUserPermService;
	}

}
