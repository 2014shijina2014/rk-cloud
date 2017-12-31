package org.rk.cloud.userservice.unit.infokey.dao;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUnitInfokeyDao extends IDBDao<CoreUnitInfokey> {

	public boolean deleteById(Long id);
}
