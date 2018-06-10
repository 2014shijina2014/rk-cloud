/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.rolePerm.controller;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.rolePerm.service.ICoreRolePermService;
import org.rk.core.pubServer.controller.BaseController;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/web/core/user/rolePerm/")
public class CoreRolePermWeb extends BaseController<CoreRolePerm> {
	@Resource(name = "CoreRolePermService")
	private ICoreRolePermService coreRolePermService;

	public ICoreRolePermService getCoreRolePermService() {
		return coreRolePermService;
	}

}
