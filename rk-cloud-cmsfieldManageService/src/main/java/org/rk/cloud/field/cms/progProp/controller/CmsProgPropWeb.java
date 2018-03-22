package org.rk.cloud.field.cms.progProp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.cloud.field.cms.progProp.service.ICmsProgPropService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rk/web/field/cms/progProp/")
public class CmsProgPropWeb extends BaseController<CmsProgProp> {
	@Resource(name = "CmsProgPropService")
	private ICmsProgPropService cmsProgPropService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public Object queryPage(int start, int length, int draw,String programaCode,String propertyCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(RkStrUtil.hasText(programaCode)){
			params.put("programaCode", programaCode);
		}
		if(RkStrUtil.hasText(propertyCode)){
			params.put("propertyCode", propertyCode);
		}
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = cmsProgPropService.selectListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public Object queryList(String programaCode,String propertyCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(RkStrUtil.hasText(programaCode)){
			params.put("programaCode", programaCode);
		}
		if(RkStrUtil.hasText(propertyCode)){
			params.put("propertyCode", propertyCode);
		}
		List<Map<String, Object>> resultList = cmsProgPropService.selectList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public Object query(Long id) {
		CmsProgProp cmsProgProp = cmsProgPropService.selectModel(id);
		if (RkObjectUtil.isEmpty(cmsProgProp)) {
			return ajaxError("没有找到数据");
		}
		return cmsProgProp;
	}

	public ICmsProgPropService getCmsProgPropService() {
		return cmsProgPropService;
	}

}
