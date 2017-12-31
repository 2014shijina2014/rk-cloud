/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.dictionary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
import org.rk.cloud.sysservice.dictionary.service.ICoreSysDictionaryService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月27日
 */
@Controller
@RequestMapping("/rk/web/core/sys/dictionary/")
public class CoreSysDictionaryWeb extends BaseController<CoreSysDictionary> {
	@Resource(name = "CoreSysDictionaryService")
	private ICoreSysDictionaryService coreSysDictionaryService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String fatherCode, String code, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fatherCode", fatherCode + "%");
		params.put("code", "%" + code + "%");
		params.put("name", "%" + name + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreSysDictionaryService.selectModelListPage(params, page);
		return page;
	}

	@RequiresPermissions(value = "coreSysDictionary:queryTree")
	@RequestMapping(value = "queryTree", method = RequestMethod.POST)
	public @ResponseBody Object queryTree(Map<String, Object> params) {
		List<CoreSysDictionary> menuTree = coreSysDictionaryService.selectModelList(params);
		return menuTree;
	}

	@RequestMapping(value = "queryDicByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryDicByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<CoreSysDictionary> dicList = coreSysDictionaryService.selectModelList(params);
		if (RkObjectUtil.isEmpty(dicList)) {
			return ajaxError("没有找到数据");
		}
		return dicList.get(0);
	}

	@RequestMapping(value = "queryDicListByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryDicListByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code + "%");
		List<CoreSysDictionary> dicList = coreSysDictionaryService.selectModelList(params);
		if (RkObjectUtil.isEmpty(dicList)) {
			return ajaxError("没有找到数据");
		}
		return dicList;
	}

	@RequestMapping(value = "queryDicListByValue", method = RequestMethod.POST)
	public @ResponseBody Object queryDicListByValue(String value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", value);
		List<CoreSysDictionary> dicList = coreSysDictionaryService.selectModelList(params);
		if (RkObjectUtil.isEmpty(dicList)) {
			return ajaxError("没有找到数据");
		} else {
			CoreSysDictionary coreSysDictionary = dicList.get(0);
			params.put("code", coreSysDictionary.getCode() + "%");
			params.remove("value");
			List<CoreSysDictionary> dicResultList = coreSysDictionaryService.selectModelList(params);
			return dicResultList;
		}
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreSysDictionary coreSysDictionary = coreSysDictionaryService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreSysDictionary)) {
			return ajaxError("没有找到数据");
		}
		return coreSysDictionary;
	}

	public ICoreSysDictionaryService getCoreSysDictionaryService() {
		return coreSysDictionaryService;
	}

}
