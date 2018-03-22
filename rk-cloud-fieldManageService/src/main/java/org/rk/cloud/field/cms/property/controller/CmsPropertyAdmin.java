package org.rk.cloud.field.cms.property.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rk/admin/field/cms/property/")
public class CmsPropertyAdmin extends CmsPropertyWeb {
	
	@RequiresPermissions(value="cmsProperty:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(CmsProperty mallProperty) {
		if(RkObjectUtil.isEmpty(mallProperty.getId())){
			mallProperty.setCreator(SecurityUtil.getUserName());
			getCmsPropertyService().insertModel(mallProperty);
		}else{
			mallProperty.setUpdator(SecurityUtil.getUserName());
			getCmsPropertyService().updateModel(mallProperty);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="cmsProperty:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCmsPropertyService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
