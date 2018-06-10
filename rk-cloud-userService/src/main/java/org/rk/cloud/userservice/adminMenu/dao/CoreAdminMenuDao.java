package org.rk.cloud.userservice.adminMenu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.bean.QueryData;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.jdbc.dao.util.SQLUtils;
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
	
	@Override
	public List<CoreAdminMenu> queryForListByPermIds(List<Long> permIdList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		for (Long permId : permIdList) {
			ParamMap pm=new ParamMap(Condition.condi_mode_eq_in,Condition.condi_type_and,"permId",permId);
			paramMapList.add(pm);
		}
		String selectSql = SQLUtils.buildSelectSql(getClass());
		String querySql = Condition.buildConditionSqlNoLimit(paramMapList, selectSql);
		QueryData queryData = super.queryBySql(querySql, paramMapList);
		List<Map<String, Object>> queryMapList = queryData.getQueryBeanList();
		List<CoreAdminMenu> resultList = new ArrayList<CoreAdminMenu>();
		for (Map<String, Object> map : queryMapList) {
			CoreAdminMenu coreAdminMenu = (CoreAdminMenu) RKBeanUtil.convertMapToBean(map, CoreAdminMenu.class);
			resultList.add(coreAdminMenu);
		}
		return resultList;
	}
}
