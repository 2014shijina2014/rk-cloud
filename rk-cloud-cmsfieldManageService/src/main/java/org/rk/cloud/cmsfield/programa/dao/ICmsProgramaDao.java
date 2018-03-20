package org.rk.cloud.cmsfield.programa.dao;

import org.rk.cloud.cmsfield.programa.CmsPrograma;
import org.rk.core.jdbc.dao.IDBDao;

public interface ICmsProgramaDao extends IDBDao<CmsPrograma> {

	public boolean deleteById(Long id);
}
