package org.rk.cloud.sysservice.area.dao;

import org.rk.cloud.sysservice.area.CoreSysArea;
import org.rk.core.jdbc.dao.IDBDao;

public interface ICoreSysAreaDao extends IDBDao<CoreSysArea> {

	public boolean deleteById(Long id);
}
