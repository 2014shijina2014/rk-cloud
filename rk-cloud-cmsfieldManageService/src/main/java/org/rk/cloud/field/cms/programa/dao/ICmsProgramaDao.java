package org.rk.cloud.field.cms.programa.dao;

import org.rk.cloud.field.cms.programa.CmsPrograma;
import org.rk.core.jdbc.dao.IDBDao;

public interface ICmsProgramaDao extends IDBDao<CmsPrograma> {

	public boolean deleteById(Long id);
}
