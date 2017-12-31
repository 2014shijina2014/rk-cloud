package org.rk.cloud.sysservice.dictionary.dao;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreSysDictionaryDao")
public class CoreSysDictionaryDao extends DBDao<CoreSysDictionary> implements ICoreSysDictionaryDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreSysDictionary model=new CoreSysDictionary();
		model.setId(id);
		return super.deleteModel(model);
	}
}
