package org.rk.cloud.userservice.user.userRole.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.userRole.CoreUserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserRoleDao")
public class CoreUserRoleDao extends DBDao<CoreUserRole> implements ICoreUserRoleDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserRole model=new CoreUserRole();
		model.setId(id);
		return super.deleteModel(model);
	}
}
