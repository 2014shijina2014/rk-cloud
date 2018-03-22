package org.rk.cloud.field.cms.property.dao;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CmsPropertyDao")
public class CmsPropertyDao extends DBDao<CmsProperty> implements ICmsPropertyDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CmsProperty model=new CmsProperty();
		model.setId(id);
		return super.deleteModel(model);
	}
}
