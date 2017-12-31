package org.rk.cloud.sysservice.param.dao;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.core.jdbc.dao.IDBDao;

public interface ICoreSysParamDao extends IDBDao<CoreSysParam> {

	public boolean deleteById(Long id);
}
