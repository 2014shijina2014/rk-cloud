/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.field.cms.programa.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.field.cms.programa.CmsPrograma;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.anno.docAnno.ApiDocAnno;
import org.rk.core.common.anno.docAnno.DocType;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * 2017年1月26日
 */
@ApiDocAnno(name="CmsProgramaAdmin",desc="封闭端栏目分类", docType = DocType.TYPE)
@Controller
@RequestMapping("/rk/admin/field/cms/programa/")
public class CmsProgramaAdmin extends CmsProgramaWeb {
	@RequiresPermissions(value="articlePrograma:save")
	@RequestMapping(value="save",  method = RequestMethod.POST)
	public @ResponseBody Object save(CmsPrograma cmsPrograma) {
		if (RkObjectUtil.isEmpty(cmsPrograma.getId())) {
			cmsPrograma.setCreator(SecurityUtil.getUserName());
			getCmsProgramaService().insertTreeModel(cmsPrograma);
		} else {
			cmsPrograma.setUpdator(SecurityUtil.getUserName());
			getCmsProgramaService().updateModel(cmsPrograma);
		}
		return ajaxSucc("保存成功");
	}
	@RequiresPermissions(value = "articlePrograma:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCmsProgramaService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
