/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.officeservice.excel.mapping.controller;

import java.util.List;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：cavion（曹仁道）
 * @类描述：
 */
@Controller
@RequestMapping("/rk/admin/tool/office/excel/mapping/")
public class ToolOfficeExcelMappingAdmin extends ToolOfficeExcelMappingWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(ToolOfficeExcelMapping toolOfficeExcelMapping) {
		if(RkObjectUtil.isEmpty(toolOfficeExcelMapping.getId())){
			toolOfficeExcelMapping.setCreator(SecurityUtil.getUserName());
			getToolOfficeExcelMappingService().insertModel(toolOfficeExcelMapping);
		}else{
			toolOfficeExcelMapping.setUpdator(SecurityUtil.getUserName());
			getToolOfficeExcelMappingService().updateModel(toolOfficeExcelMapping);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getToolOfficeExcelMappingService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
