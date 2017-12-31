package org.rk.cloud.userservice.user.infokey.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserInfokeyDao")
public class CoreUserInfokeyDao extends DBDao<CoreUserInfokey> implements ICoreUserInfokeyDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserInfokey model=new CoreUserInfokey();
		model.setId(id);
		return super.deleteModel(model);
	}
}
