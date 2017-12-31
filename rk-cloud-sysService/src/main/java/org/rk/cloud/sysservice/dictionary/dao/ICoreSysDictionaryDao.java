package org.rk.cloud.sysservice.dictionary.dao;

import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
import org.rk.core.jdbc.dao.IDBDao;

public interface ICoreSysDictionaryDao extends IDBDao<CoreSysDictionary> {

	public boolean deleteById(Long id);
}
