/**
 * 
 */
package org.rk.cloud.userservice.user.role.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.userRole.CoreRole;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreRoleDao extends IDBDao<CoreRole> {

	boolean deleteById(Long id);

}
