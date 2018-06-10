package org.rk.cloud.userservice.user.permission.dao;

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
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CorePermDao")
public class CorePermDao extends DBDao<CorePerm> implements ICorePermDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CorePerm model=new CorePerm();
		model.setId(id);
		return super.deleteModel(model);
	}
	
	@Override
	public List<CorePerm> queryForListByPermIds(List<Long> permIdList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		for (Long permId : permIdList) {
			ParamMap pm=new ParamMap(Condition.condi_mode_eq_in,Condition.condi_type_and,"id",permId);
			paramMapList.add(pm);
		}
		String selectSql = SQLUtils.buildSelectSql(getClass());
		String querySql = Condition.buildConditionSqlNoLimit(paramMapList, selectSql);
		QueryData queryData = super.queryBySql(querySql, paramMapList);
		List<Map<String, Object>> queryMapList = queryData.getQueryBeanList();
		List<CorePerm> resultList = new ArrayList<CorePerm>();
		for (Map<String, Object> map : queryMapList) {
			CorePerm corePerm = (CorePerm) RKBeanUtil.convertMapToBean(map, CorePerm.class);
			resultList.add(corePerm);
		}
		return resultList;
	}
}
