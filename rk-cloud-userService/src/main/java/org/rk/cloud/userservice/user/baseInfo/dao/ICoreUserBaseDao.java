package org.rk.cloud.userservice.user.baseInfo.dao;

import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUserBaseDao extends IDBDao<CoreUserBase> {

	public boolean deleteById(Long id);
}
