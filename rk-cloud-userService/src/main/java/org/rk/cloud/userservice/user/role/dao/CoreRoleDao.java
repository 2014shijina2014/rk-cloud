package org.rk.cloud.userservice.user.role.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.userRole.CoreRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreRoleDao")
public class CoreRoleDao extends DBDao<CoreRole> implements ICoreRoleDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreRole model=new CoreRole();
		model.setId(id);
		return super.deleteModel(model);
	}
}
