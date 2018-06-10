/**
 * 
 */
package org.rk.cloud.userservice.adminMenu.service;

import java.util.List;

import org.rk.core.common.base.IBaseService;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreAdminMenuService extends IBaseService<CoreAdminMenu>{

	boolean deleteById(Long id);

	/**
	 * @方法描述：根据权限编码获取菜单
	 * @author:Cavion(曹仁道)
	 * @param permCode
	 * @return
	 * 2016年12月10日
	 */
	List<CoreAdminMenu> selectListByPermId(Long permId);

	Long insertTreeModel(CoreAdminMenu coreAdminMenu);

	List<CoreAdminMenu> selectMenu(String moduleCode,String fatherCode);

	List<CoreAdminMenu> selectByListByPermIds(List<Long> permIdList);

}
