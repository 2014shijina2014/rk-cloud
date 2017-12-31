/**
 * 
 */
package org.rk.cloud.userservice.user.userRole.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.userRole.CoreUserRole;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreUserRoleDao extends IDBDao<CoreUserRole> {

	boolean deleteById(Long id);

}
