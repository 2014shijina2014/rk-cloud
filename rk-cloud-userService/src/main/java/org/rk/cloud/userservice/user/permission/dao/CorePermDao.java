package org.rk.cloud.userservice.user.permission.dao;

import javax.annotation.Resource;

import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CorePermDao")
public class CorePermDao extends DBDao<CorePerm> implements ICorePermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CorePerm model=new CorePerm();
		model.setId(id);
		return super.deleteModel(model);
	}
}
