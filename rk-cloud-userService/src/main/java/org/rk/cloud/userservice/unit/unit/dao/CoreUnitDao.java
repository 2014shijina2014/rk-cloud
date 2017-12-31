package org.rk.cloud.userservice.unit.unit.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUnitDao")
public class CoreUnitDao extends DBDao<CoreUnit> implements ICoreUnitDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUnit model=new CoreUnit();
		model.setId(id);
		return super.deleteModel(model);
	}
}
