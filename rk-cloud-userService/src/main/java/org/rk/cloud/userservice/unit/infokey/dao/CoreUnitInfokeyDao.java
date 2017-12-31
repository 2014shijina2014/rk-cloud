package org.rk.cloud.userservice.unit.infokey.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUnitInfokeyDao")
public class CoreUnitInfokeyDao extends DBDao<CoreUnitInfokey> implements ICoreUnitInfokeyDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUnitInfokey model=new CoreUnitInfokey();
		model.setId(id);
		return super.deleteModel(model);
	}
}
