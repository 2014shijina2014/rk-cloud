package org.rk.cloud.officeservice.excel.mapping.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("ToolOfficeExcelMappingDao")
public class ToolOfficeExcelMappingDao extends DBDao<ToolOfficeExcelMapping> implements IToolOfficeExcelMappingDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		ToolOfficeExcelMapping model=new ToolOfficeExcelMapping();
		model.setId(id);
		return super.deleteModel(model);
	}
}
