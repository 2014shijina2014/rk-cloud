package org.rk.cloud.userservice.unit.unitAdminPerm.dao;

import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUnitAdminPermDao extends IDBDao<CoreUnitAdminPerm> {

	public boolean deleteById(Long id);
}
