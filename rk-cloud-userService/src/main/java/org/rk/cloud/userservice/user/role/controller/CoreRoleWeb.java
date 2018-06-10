/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.user.role.service.ICoreRoleService;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.userRole.CoreRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping(value="/rk/web/core/user/role/")
public class CoreRoleWeb extends BaseController<CoreRole> {
	@Resource(name = "CoreRoleService")
	private ICoreRoleService coreRoleService;
	
	@RequiresPermissions(value="coreRoleWeb:queryTree")
	@RequestMapping(value="queryTree",  method = RequestMethod.POST)
	public @ResponseBody Object queryTree(Map<String,Object> params){
		List<CoreRole> menuTree=coreRoleService.selectModelList(params);
		return menuTree;
	}
	@RequiresPermissions(value="coreRoleWeb:queryRoleByCode")
	@RequestMapping(value="queryRoleByCode",  method = RequestMethod.POST)
	public @ResponseBody Object queryRoleByCode(String code){
		 Map<String, Object> params=new HashMap<String, Object>();
		 params.put("code", code);
		List<CoreRole> roleList=coreRoleService.selectModelList(params);
		if(RkObjectUtil.isEmpty(roleList)){
			return ajaxSucc("没有找到数据");
		}
		return roleList.get(0);
	}
	public ICoreRoleService getCoreRoleService() {
		return coreRoleService;
	}
	
}
