package org.rk.cloud.userservice.user.userAddress.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserAddressDao")
public class CoreUserAddressDao extends DBDao<CoreUserAddress> implements ICoreUserAddressDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserAddress model=new CoreUserAddress();
		model.setId(id);
		return super.deleteModel(model);
	}
}
