/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.unit.infokey.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.cloud.userservice.unit.infokey.service.ICoreUnitInfokeyService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.util.Condition;
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
@RequestMapping("/rk/web/core/unit/unitInfoKey/")
public class CoreUnitInfokeyWeb extends BaseController<CoreUnitInfokey> {
	@Resource(name = "CoreUnitInfokeyService")
	private ICoreUnitInfokeyService coreUnitInfokeyService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String keyCode, String keyName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyCode", "%" + keyCode + "%");
		params.put("keyName", "%" + keyName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUnitInfokeyService.selectModelListPage(params, page);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(String keyCode, String keyName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyCode", "%" + keyCode + "%");
		params.put("keyName", "%" + keyName + "%");
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("showIndex", Condition.condi_mode_order_asc);
		orderList.add(orderMap);
		List<CoreUnitInfokey> result = coreUnitInfokeyService.selectModelList(params, orderList);
		return result;
	}
	@RequestMapping(value = "queryByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryByCode(String keyCode) {
		CoreUnitInfokey unitInfokey = coreUnitInfokeyService.selectByCode(keyCode);
		return unitInfokey;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUnitInfokey unitInfokey = coreUnitInfokeyService.selectModel(id);
		return unitInfokey;
	}

	public ICoreUnitInfokeyService getCoreUnitInfokeyService() {
		return coreUnitInfokeyService;
	}

}
