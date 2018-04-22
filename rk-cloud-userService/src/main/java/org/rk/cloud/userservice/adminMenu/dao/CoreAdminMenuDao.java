package org.rk.cloud.userservice.adminMenu.dao;

import javax.annotation.Resource;

import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreAdminMenuDao")
public class CoreAdminMenuDao extends DBDao<CoreAdminMenu> implements ICoreAdminMenuDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreAdminMenu model=new CoreAdminMenu();
		model.setId(id);
		return super.deleteModel(model);
	}
}
