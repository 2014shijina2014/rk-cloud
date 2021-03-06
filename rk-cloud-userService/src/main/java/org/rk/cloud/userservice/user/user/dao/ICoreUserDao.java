/**
 * 
 */
package org.rk.cloud.userservice.user.user.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.user.CoreUser;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreUserDao extends IDBDao<CoreUser> {

	boolean deleteById(Long id);

}
