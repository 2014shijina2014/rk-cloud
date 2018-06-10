/**
 * caorendao187@163.com
 * @author:cavion
 * @描述：
 * 2018年6月10日 下午3:43:41
 */
package org.rk.cloud.userservice.auth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.adminMenu.service.ICoreAdminMenuService;
import org.rk.cloud.userservice.domain.service.ICoreDomainService;
import org.rk.cloud.userservice.module.service.ICoreModuleService;
import org.rk.cloud.userservice.user.permission.service.ICorePermService;
import org.rk.cloud.userservice.user.rolePerm.service.ICoreRolePermService;
import org.rk.cloud.userservice.user.user.service.ICoreUserService;
import org.rk.cloud.userservice.user.userRole.service.ICoreUserRoleService;
import org.rk.core.auth.authIntf.IAuthService;
import org.rk.core.auth.bean.ActiveUser;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.domain.domain.CoreDomain;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.domain.module.CoreModule;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.rk.core.user.user.CoreUser;
import org.rk.core.user.userPerm.CorePerm;
import org.rk.core.user.userRole.CoreUserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author cavion
 * @描述：
 * 2018年6月10日 下午3:43:41
 */
@Component
public class AuthServiceImpl implements IAuthService{
	@Resource(name="CoreUserService")
	private ICoreUserService coreUserService;
	@Resource(name="CoreDomainService")
	private ICoreDomainService coreDomainService;
	@Resource(name="CoreUserRoleService")
	private ICoreUserRoleService coreUserRoleService;
	@Resource(name="CoreRolePermService")
	private ICoreRolePermService coreRolePermService;
	@Resource(name="CorePermService")
	private ICorePermService corePermService;
	@Resource(name="CoreAdminMenuService")
	private ICoreAdminMenuService coreAdminMenuService;
	@Resource(name="CoreModuleService")
	private ICoreModuleService coreModuleService;
	
	@Override
	@Cacheable
	public ActiveUser queryCurrUser(String userName) {
		CoreUser coreUser=coreUserService.selectUserByUserName(userName);
		if(RkObjectUtil.isEmpty(coreUser)){
			return null;
		}
		CoreDomain coreDomain=coreDomainService.selectModel(coreUser.getFromDomain());
		String password = coreUser.getPassword();
		ActiveUser activeUser = new ActiveUser();
		activeUser.setSalt(coreUser.getSalt());
		activeUser.setUserNum(coreUser.getUserNum());
		activeUser.setFromDomain(coreDomain);
		activeUser.setId(coreUser.getId());
		activeUser.setLoginDate(new Date());
		activeUser.setUserName(coreUser.getUserName());
		activeUser.setPassword(password);
		//获取菜单
		List<CoreAdminMenu> menuList = new ArrayList<CoreAdminMenu>();
		List<CoreModule> moduleList = new ArrayList<CoreModule>();
		List<CorePerm> permList = new ArrayList<CorePerm>();
		List<CoreUserRole> userRoleList = coreUserRoleService.selectByUserNum(coreUser.getUserNum());
		//获取用户角色
		List<Long> roleIdList = new ArrayList<Long>();
		for (CoreUserRole coreUserRole : userRoleList) {
			roleIdList.add(coreUserRole.getRoleId());
		}
		List<CoreRolePerm> rolePermList = new ArrayList<CoreRolePerm>();
		if(!RkObjectUtil.isEmpty(roleIdList)) {
			rolePermList = coreRolePermService.selectByListByRoleIds(roleIdList);
		}
		//根据用户角色获取用户权限和菜单
		List<Long> permIdList = new ArrayList<Long>();
		for (CoreRolePerm coreRolePerm : rolePermList) {
			permIdList.add(coreRolePerm.getPermId());
		}
		List<CoreAdminMenu> coreAdminMenuList = new ArrayList<CoreAdminMenu>();
		if(!RkObjectUtil.isEmpty(permIdList)) {
			permList = corePermService.selectByListByPermIds(permIdList);
			coreAdminMenuList = coreAdminMenuService.selectByListByPermIds(permIdList);
		}
		List<String> moduleCodeList = new ArrayList<String>();
		for (CoreAdminMenu coreAdminMenu : coreAdminMenuList) {
			if(!menuList.contains(coreAdminMenu)){
				menuList.add(coreAdminMenu);
			}
			if(!moduleCodeList.contains(coreAdminMenu.getModuleCode())){
				moduleCodeList.add(coreAdminMenu.getModuleCode());
			}
			//递归获取菜单上级，一直到root节点
			queryMenu(coreAdminMenu.getFatherCode(), menuList);
		}
		//获取所有模块
		moduleList = coreModuleService.selectByListByModuleCodes(moduleCodeList);
		
		activeUser.setMenuList(menuList);
		activeUser.setModuleList(moduleList);
		activeUser.setUserRoleList(userRoleList);
		activeUser.setPermList(permList);
		
		return activeUser;
	}
	/**
	 * 
	 * @author：cavion
	 * @描述：递归获取父节点菜单
	 * 2018年6月10日 下午6:18:38
	 * @param code
	 * @param menuList
	 */
	private void queryMenu(String code, List<CoreAdminMenu> menuList) {
		if(RkStrUtil.isEmpty(code)) {
			return;
		}
		CoreAdminMenu menu=coreAdminMenuService.selectModel("code", code);
		if(RkObjectUtil.isEmpty(menu)) {
			return;
		}
		if(!menuList.contains(menu)) {
			menuList.add(menu);
			queryMenu(menu.getFatherCode(), menuList);
		}
	} 
}
