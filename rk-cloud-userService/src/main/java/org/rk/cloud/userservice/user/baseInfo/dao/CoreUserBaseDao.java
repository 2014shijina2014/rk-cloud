package org.rk.cloud.userservice.user.baseInfo.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserBaseDao")
public class CoreUserBaseDao extends DBDao<CoreUserBase> implements ICoreUserBaseDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserBase model=new CoreUserBase();
		model.setId(id);
		return super.deleteModel(model);
	}
}
