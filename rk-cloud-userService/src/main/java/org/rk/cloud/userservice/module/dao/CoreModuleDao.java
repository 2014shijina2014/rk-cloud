package org.rk.cloud.userservice.module.dao;

import javax.annotation.Resource;

import org.rk.core.domain.module.CoreModule;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreModuleDao")
public class CoreModuleDao extends DBDao<CoreModule> implements ICoreModuleDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreModule model=new CoreModule();
		model.setId(id);
		return super.deleteModel(model);
	}
}
