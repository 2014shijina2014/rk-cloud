/**
 * 
 */
package org.rk.cloud.userservice.user.permission.dao;

import java.util.List;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.userPerm.CorePerm;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICorePermDao extends IDBDao<CorePerm> {

	boolean deleteById(Long id);

	List<CorePerm> queryForListByPermIds(List<Long> permIdList);

}
