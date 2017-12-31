package org.rk.cloud.userservice.unit.unit.dao;

import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUnitDao extends IDBDao<CoreUnit> {

	public boolean deleteById(Long id);
}
