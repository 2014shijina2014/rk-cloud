/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.role.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.userRole.CoreRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping(value="/rk/admin/core/user/role/")
public class CoreRoleAdmin extends CoreRoleWeb {
	@RequiresPermissions(value="coreRoleAdmin:save")
	@RequestMapping(value="save",  method = RequestMethod.POST)
	 public @ResponseBody Object save(CoreRole coreRole){
		if(RkObjectUtil.isEmpty(coreRole.getId())){
			coreRole.setCreator(SecurityUtil.getUserName());
			getCoreRoleService().insertTreeModel(coreRole);
		 }else{
			 coreRole.setUpdator(SecurityUtil.getUserName());
			 getCoreRoleService().updateModel(coreRole);
		 }
		return ajaxSucc("保存成功");
	 }
	@RequiresPermissions(value="coreRoleAdmin:delete")
	 @RequestMapping(value="delete",  method = RequestMethod.POST)
	 public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr){
		for (Long id : idArr) {
			 getCoreRoleService().deleteById(id);
		}
		 return ajaxSucc("删除成功");
	 }
}
