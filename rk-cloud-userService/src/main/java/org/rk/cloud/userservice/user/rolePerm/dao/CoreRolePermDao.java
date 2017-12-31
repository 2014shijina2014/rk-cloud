package org.rk.cloud.userservice.user.rolePerm.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreRolePermDao")
public class CoreRolePermDao extends DBDao<CoreRolePerm> implements ICoreRolePermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreRolePerm model=new CoreRolePerm();
		model.setId(id);
		return super.deleteModel(model);
	}
}
