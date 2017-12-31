/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.userRole.controller;

import javax.annotation.Resource;

import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.service.ICoreUserRoleService;
import org.rk.core.user.userRole.CoreUserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/web/core/user/userRole/")
public class CoreUserRoleWeb extends BaseController<CoreUserRole> {
	@Resource(name = "CoreUserRoleService")
	 private ICoreUserRoleService coreUserRoleService;

	public ICoreUserRoleService getCoreUserRoleService() {
		return coreUserRoleService;
	}
	
	
}
