/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.unit.info.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.cloud.userservice.unit.info.service.ICoreUnitInfoService;
import org.rk.cloud.userservice.unit.unit.service.ICoreUnitService;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月11日
 */
@Controller
@RequestMapping("/rk/web/core/unit/unitInfo/")
public class CoreUnitInfoWeb extends BaseController<CoreUnitInfo> {
	@Resource(name = "CoreUnitInfoService")
	private ICoreUnitInfoService coreUnitInfoService;
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
		page = coreUnitInfoService.selectListPage(params, page);
		return page;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long unitId) {
		Map<String,Object> userInfo = coreUnitInfoService.selectUnitInfo(unitId);
		return userInfo;
	}
	@RequestMapping(value = "queryUnit", method = RequestMethod.POST)
	public @ResponseBody Object queryUnit(Long unitId) {
		Map<String,Object> unitInfo = coreUnitInfoService.selectUnitInfo(unitId);//待处理，需要找当前用户的管理单位
		return unitInfo;
	}

	public ICoreUnitInfoService getCoreUnitInfoService() {
		return coreUnitInfoService;
	}

	public ICoreUnitService getCoreUnitService() {
		return coreUnitService;
	}

	
}
