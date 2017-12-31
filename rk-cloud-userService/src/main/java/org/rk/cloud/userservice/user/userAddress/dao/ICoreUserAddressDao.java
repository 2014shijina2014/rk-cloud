package org.rk.cloud.userservice.user.userAddress.dao;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUserAddressDao extends IDBDao<CoreUserAddress> {

	public boolean deleteById(Long id);
}
