package org.rk.cloud.field.cms.property.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.cloud.field.cms.property.service.ICmsPropertyService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rk/web/field/cms/property/")
public class CmsPropertyWeb extends BaseController<CmsProperty> {
	@Resource(name = "CmsPropertyService")
	private ICmsPropertyService cmsPropertyService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = cmsPropertyService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CmsProperty> resultList = cmsPropertyService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public Object query(Long id) {
		CmsProperty mallProperty = cmsPropertyService.selectModel(id);
		if (RkObjectUtil.isEmpty(mallProperty)) {
			return ajaxError("没有找到数据");
		}
		return mallProperty;
	}

	public ICmsPropertyService getCmsPropertyService() {
		return cmsPropertyService;
	}

}
