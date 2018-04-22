/**
 * 
 */
package org.rk.cloud.userservice.domain.dao;

import org.rk.core.domain.domain.CoreDomain;
import org.rk.core.jdbc.dao.IDBDao;
/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreDomainDao extends IDBDao<CoreDomain> {

	boolean deleteById(Long id);

}
