package org.rk.cloud.userservice.unit.unitAdminPerm.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUnitAdminPermDao")
public class CoreUnitAdminPermDao extends DBDao<CoreUnitAdminPerm> implements ICoreUnitAdminPermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUnitAdminPerm model=new CoreUnitAdminPerm();
		model.setId(id);
		return super.deleteModel(model);
	}
}
