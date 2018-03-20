package org.rk.cloud.cmsfield.programa.dao;

import javax.annotation.Resource;

import org.rk.cloud.cmsfield.programa.CmsPrograma;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CmsProgramaDao")
public class CmsProgramaDao extends DBDao<CmsPrograma> implements ICmsProgramaDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CmsPrograma model=new CmsPrograma();
		model.setId(id);
		return super.deleteModel(model);
	}
}
