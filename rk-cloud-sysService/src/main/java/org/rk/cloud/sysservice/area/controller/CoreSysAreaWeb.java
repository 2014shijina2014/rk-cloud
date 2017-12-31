/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.area.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.area.CoreSysArea;
import org.rk.cloud.sysservice.area.service.ICoreSysAreaService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月27日
 */
@Controller
@RequestMapping("/rk/web/core/sys/area/")
public class CoreSysAreaWeb extends BaseController<CoreSysArea> {
	@Resource(name = "CoreSysAreaService")
	private ICoreSysAreaService coreSysAreaService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String fatherCode, String code, String name,String areaNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fatherCode", fatherCode + "%");
		params.put("code", "%" + code + "%");
		params.put("name", "%" + name + "%");
		params.put("areaNum", "%" + areaNum + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderBean=new OrderBean("areaNum", Condition.condi_mode_order_asc);
		orderList.add(orderBean);
		page = coreSysAreaService.selectModelListPage(params, page,orderList);
		return page;
	}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(String areaType, String areaShortName, String areaNum, String name) {
		Map<String, Object> areaParams = new HashMap<String, Object>();
		areaParams.put("areaType", areaType);
		areaParams.put("areaShortName", "%" + areaShortName + "%");
		areaParams.put("areaNum", "%" + areaNum + "%");
		areaParams.put("name", "%" + name + "%");
		List<CoreSysArea> result = coreSysAreaService.selectModelList(areaParams);
		return result;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreSysArea coreSysArea = coreSysAreaService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreSysArea)) {
			return ajaxError("没有找到数据");
		}
		return coreSysArea;
	}
	@RequestMapping(value = "queryTree", method = RequestMethod.POST)
	public @ResponseBody Object queryTree(Map<String, Object> params) {
		List<CoreSysArea> menuTree = coreSysAreaService.selectModelList(params);
		return menuTree;
	}

	@RequestMapping(value = "queryByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<CoreSysArea> dicList = coreSysAreaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(dicList)) {
			return ajaxError("没有找到数据");
		}
		return dicList.get(0);
	}

	@RequestMapping(value = "queryChildByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryChildByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fatherCode", code);
		List<CoreSysArea> areaList = coreSysAreaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(areaList)) {
			return ajaxError("没有找到数据");
		}
		return areaList;
	}
	@RequestMapping(value = "queryAllChildByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryAllChildByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code + "%");
		List<CoreSysArea> areaList = coreSysAreaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(areaList)) {
			return ajaxError("没有找到数据");
		}
		return areaList;
	}
	@RequestMapping(value = "queryByAreaNum", method = RequestMethod.POST)
	public @ResponseBody Object queryByAreaNum(String areaNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaNum", areaNum);
		List<CoreSysArea> list = coreSysAreaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(list)) {
			return ajaxError("没有找到数据");
		}
		return list.get(0);
	}
	public ICoreSysAreaService getCoreSysAreaService() {
		return coreSysAreaService;
	}

}
