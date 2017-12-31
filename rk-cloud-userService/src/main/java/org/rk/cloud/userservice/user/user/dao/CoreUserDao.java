package org.rk.cloud.userservice.user.user.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.user.CoreUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserDao")
public class CoreUserDao extends DBDao<CoreUser> implements ICoreUserDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUser model=new CoreUser();
		model.setId(id);
		return super.deleteModel(model);
	}
}
