/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.officeservice.excel.mapping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.common.config.AppPropConfigInfo;
import org.rk.cloud.officeservice.excel.mapping.testcloud.ITestCloudService;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
import org.rk.core.tools.office.mapping.service.IToolOfficeExcelMappingService;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ITestCloudService testCloudService;
	//@Autowired
	//private ITestCloudService2 testCloudService2;

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

	@RequestMapping(value = "query", method = RequestMethod.GET)
	public @ResponseBody Object query(Long id) {
		ToolOfficeExcelMapping toolOfficeExcelMapping = toolOfficeExcelMappingService.selectModel(id);
		return toolOfficeExcelMapping;
	}
	
	@RequestMapping(value = "queryTestCloud", method = RequestMethod.GET)
	public @ResponseBody Object queryTestCloud() {
		List<Map<String, Object>> result = testCloudService.queryList(AppPropConfigInfo.getEnv(), AppPropConfigInfo.getAppName(), "");
		return result;
	}
	/*@RequestMapping(value = "queryTestCloud2", method = RequestMethod.GET)
	public @ResponseBody Object queryTestCloud2() {
		List<Map<String, Object>> result = testCloudService2.queryList("", "");
		return result;
	}*/

	public IToolOfficeExcelMappingService getToolOfficeExcelMappingService() {
		return toolOfficeExcelMappingService;
	}

}
