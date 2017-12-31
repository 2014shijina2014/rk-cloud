package org.rk.cloud.userservice.user.infokey.dao;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUserInfokeyDao extends IDBDao<CoreUserInfokey> {

	public boolean deleteById(Long id);
}
