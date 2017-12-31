/**
 * 
 */
package org.rk.cloud.userservice.user.rolePerm.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.rolePerm.CoreRolePerm;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreRolePermDao extends IDBDao<CoreRolePerm> {

	boolean deleteById(Long id);

}
