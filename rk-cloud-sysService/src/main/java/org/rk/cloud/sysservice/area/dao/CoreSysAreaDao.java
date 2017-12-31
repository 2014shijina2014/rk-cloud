package org.rk.cloud.sysservice.area.dao;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.area.CoreSysArea;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreSysAreaDao")
public class CoreSysAreaDao extends DBDao<CoreSysArea> implements ICoreSysAreaDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreSysArea model=new CoreSysArea();
		model.setId(id);
		return super.deleteModel(model);
	}
}
