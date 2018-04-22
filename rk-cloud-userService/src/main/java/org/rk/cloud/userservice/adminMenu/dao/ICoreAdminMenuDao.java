/**
 * 
 */
package org.rk.cloud.userservice.adminMenu.dao;

import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.jdbc.dao.IDBDao;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreAdminMenuDao extends IDBDao<CoreAdminMenu> {

	boolean deleteById(Long id);

}
