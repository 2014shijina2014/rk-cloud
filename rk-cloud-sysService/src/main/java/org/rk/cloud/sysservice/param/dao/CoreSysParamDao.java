package org.rk.cloud.sysservice.param.dao;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreSysParamDao")
public class CoreSysParamDao extends DBDao<CoreSysParam> implements ICoreSysParamDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreSysParam model=new CoreSysParam();
		model.setId(id);
		return super.deleteModel(model);
	}
}
