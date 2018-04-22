/**
 * 
 */
package org.rk.cloud.userservice.module.dao;

import org.rk.core.domain.module.CoreModule;
import org.rk.core.jdbc.dao.IDBDao;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreModuleDao extends IDBDao<CoreModule> {

	boolean deleteById(Long id);

}
