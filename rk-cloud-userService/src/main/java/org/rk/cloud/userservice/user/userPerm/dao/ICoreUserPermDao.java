package org.rk.cloud.userservice.user.userPerm.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.user.userPerm.CoreUserPerm;
public interface ICoreUserPermDao extends IDBDao<CoreUserPerm> {

	public boolean deleteById(Long id);
}
