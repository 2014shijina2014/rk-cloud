package org.rk.cloud.userservice.user.userPerm.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.userPerm.CoreUserPerm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserPermDao")
public class CoreUserPermDao extends DBDao<CoreUserPerm> implements ICoreUserPermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserPerm model=new CoreUserPerm();
		model.setId(id);
		return super.deleteModel(model);
	}
}
