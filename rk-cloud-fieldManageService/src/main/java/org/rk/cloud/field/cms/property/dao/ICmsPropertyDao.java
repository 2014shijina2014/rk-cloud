package org.rk.cloud.field.cms.property.dao;

import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICmsPropertyDao extends IDBDao<CmsProperty> {

	public boolean deleteById(Long id);
}
