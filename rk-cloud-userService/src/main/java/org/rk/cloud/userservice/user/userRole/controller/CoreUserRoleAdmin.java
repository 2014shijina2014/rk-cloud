/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.userRole.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.userRole.CoreUserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/user/userRole/")
public class CoreUserRoleAdmin extends CoreUserRoleWeb{
	@RequiresPermissions(value="coreUserRoleAdmin:save")
	@RequestMapping(value="save",  method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserRole coreUserRole){
		if(RkObjectUtil.isEmpty(coreUserRole.getId())){
			getCoreUserRoleService().insertModel(coreUserRole);
		}else{
			getCoreUserRoleService().updateModel(coreUserRole);
		}
		return ajaxSucc("保存成功");
	}
}
