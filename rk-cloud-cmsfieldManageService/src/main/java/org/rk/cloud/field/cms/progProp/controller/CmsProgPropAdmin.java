package org.rk.cloud.field.cms.progProp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/rk/admin/field/cms/progProp/")
public class CmsProgPropAdmin extends CmsProgPropWeb {
	@RequiresPermissions(value="cmsProgProp:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(CmsProgProp cmsProgProp) {
		if(RkObjectUtil.isEmpty(cmsProgProp.getId())){
			if(!RkStrUtil.hasText(cmsProgProp.getEnabled())){
				cmsProgProp.setEnabled(RkConst.yesno.yes);
			}
			cmsProgProp.setCreator(SecurityUtil.getUserName());
			getCmsProgPropService().insertModel(cmsProgProp);
		}else{
			cmsProgProp.setUpdator(SecurityUtil.getUserName());
			getCmsProgPropService().updateModel(cmsProgProp);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="cmsProgProp:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCmsProgPropService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
