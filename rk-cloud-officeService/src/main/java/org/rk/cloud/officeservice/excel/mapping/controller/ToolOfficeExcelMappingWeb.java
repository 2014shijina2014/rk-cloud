/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.officeservice.excel.mapping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
import org.rk.core.tools.office.mapping.service.IToolOfficeExcelMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：cavion（曹仁道）
 * @类描述：
 */
@Controller
@RequestMapping("/rk/web/tool/office/excel/mapping/")
public class ToolOfficeExcelMappingWeb extends BaseController<ToolOfficeExcelMapping> {
	@Resource(name = "ToolOfficeExcelMappingService")
	private IToolOfficeExcelMappingService toolOfficeExcelMappingService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryList(int start, int length, int draw, String fieldName, String localField, String resourceField) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fieldName", "%" + fieldName + "%");
		params.put("localField", "%" + localField + "%");
		params.put("resourceField", "%" + resourceField + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = toolOfficeExcelMappingService.selectModelListPage(params, page);
		return page;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		ToolOfficeExcelMapping toolOfficeExcelMapping = toolOfficeExcelMappingService.selectModel(id);
		return toolOfficeExcelMapping;
	}

	public IToolOfficeExcelMappingService getToolOfficeExcelMappingService() {
		return toolOfficeExcelMappingService;
	}

}
