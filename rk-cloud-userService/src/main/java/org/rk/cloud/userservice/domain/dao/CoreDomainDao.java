package org.rk.cloud.userservice.domain.dao;

import javax.annotation.Resource;

import org.rk.core.domain.domain.CoreDomain;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreDomainDao")
public class CoreDomainDao extends DBDao<CoreDomain> implements ICoreDomainDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreDomain model=new CoreDomain();
		model.setId(id);
		return super.deleteModel(model);
	}
}
