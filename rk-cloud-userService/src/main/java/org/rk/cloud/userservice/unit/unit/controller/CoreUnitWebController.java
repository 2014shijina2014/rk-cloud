/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.unit.unit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.cloud.userservice.unit.unit.service.ICoreUnitService;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月10日
 */
@Controller
@RequestMapping("/rk/web/core/unit/unit/")
public class CoreUnitWebController extends BaseController<CoreUnit> {
	@Resource(name = "CoreUnitService")
	private ICoreUnitService coreUnitService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String unitName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitName", "%" + unitName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUnitService.selectModelListPage(params, page);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(String unitName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitName", "%" + unitName + "%");
		List<CoreUnit> result = coreUnitService.selectModelList(params);
		return result;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUnit coreUnit=null;
		if(RkObjectUtil.isEmpty(id)){
			Long userId=SecurityUtil.getUserId();
			coreUnit=coreUnitService.selectUnitByUserId(userId);
		}else{
			coreUnit = coreUnitService.selectModel(id);
		}
		return coreUnit;
	}

	public ICoreUnitService getCoreUnitService() {
		return coreUnitService;
	}

}
