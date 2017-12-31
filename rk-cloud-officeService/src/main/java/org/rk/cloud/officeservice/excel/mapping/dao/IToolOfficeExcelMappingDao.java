package org.rk.cloud.officeservice.excel.mapping.dao;

import org.rk.core.jdbc.dao.IDBDao;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
public interface IToolOfficeExcelMappingDao extends IDBDao<ToolOfficeExcelMapping> {

	public boolean deleteById(Long id);
}
