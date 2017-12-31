/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.rolePerm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/user/rolePerm/")
public class CoreRolePermAdmin extends CoreRolePermWeb{
	@RequiresPermissions(value = "coreRolePermAdmin:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreRolePerm coreRolePerm) {
		if(RkObjectUtil.isEmpty(coreRolePerm.getId())){
			coreRolePerm.setCreator(SecurityUtil.getUserName());
			getCoreRolePermService().inserRolePerm(coreRolePerm);
		}else{
			coreRolePerm.setUpdator(SecurityUtil.getUserName());
			getCoreRolePermService().updateModel(coreRolePerm);
		}
		return ajaxSucc("保存成功");
	}
}
