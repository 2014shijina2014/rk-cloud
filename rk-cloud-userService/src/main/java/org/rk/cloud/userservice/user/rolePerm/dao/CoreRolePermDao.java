package org.rk.cloud.userservice.user.rolePerm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.bean.QueryData;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.jdbc.dao.util.SQLUtils;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreRolePermDao")
public class CoreRolePermDao extends DBDao<CoreRolePerm> implements ICoreRolePermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreRolePerm model=new CoreRolePerm();
		model.setId(id);
		return super.deleteModel(model);
	}
	
	@Override
	public List<CoreRolePerm> queryForListByRoleIds(List<Long> roleIdList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		for (Long roleId : roleIdList) {
			ParamMap pm=new ParamMap(Condition.condi_mode_eq_in,Condition.condi_type_and,"roleId",roleId);
			paramMapList.add(pm);
		}
		String selectSql = SQLUtils.buildSelectSql(getClass());
		String querySql = Condition.buildConditionSqlNoLimit(paramMapList, selectSql);
		QueryData queryData = super.queryBySql(querySql, paramMapList);
		List<Map<String, Object>> queryMapList = queryData.getQueryBeanList();
		List<CoreRolePerm> resultList = new ArrayList<CoreRolePerm>();
		for (Map<String, Object> map : queryMapList) {
			CoreRolePerm coreRolePerm = (CoreRolePerm) RKBeanUtil.convertMapToBean(map, CoreRolePerm.class);
			resultList.add(coreRolePerm);
		}
		return resultList;
	}
}
