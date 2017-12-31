package  org.rk.cloud.userservice.userThird.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.cloud.userservice.userThird.service.ICoreUserThirdService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rk/web/core/user/coreUserThird/")
public class CoreUserThirdWeb extends BaseController<CoreUserThird> {
	@Resource(name = "CoreUserThirdService")
	private ICoreUserThirdService coreUserThirdService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUserThirdService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CoreUserThird> resultList = coreUserThirdService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUserThird coreUserThird = coreUserThirdService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreUserThird)) {
			return ajaxError("没有找到数据");
		}
		return coreUserThird;
	}
	public ICoreUserThirdService getCoreUserThirdService() {
		return coreUserThirdService;
	}

}
