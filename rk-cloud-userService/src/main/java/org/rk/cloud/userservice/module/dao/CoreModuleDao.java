package org.rk.cloud.userservice.module.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.common.bean.QueryData;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.domain.module.CoreModule;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.jdbc.dao.util.SQLUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreModuleDao")
public class CoreModuleDao extends DBDao<CoreModule> implements ICoreModuleDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreModule model=new CoreModule();
		model.setId(id);
		return super.deleteModel(model);
	}
	
	@Override
	public List<CoreModule> queryForListByModuleCodes(List<String> moduleCodeList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		for (String moduleCode : moduleCodeList) {
			ParamMap pm=new ParamMap(Condition.condi_mode_eq_in,Condition.condi_type_and,"code",moduleCode);
			paramMapList.add(pm);
		}
		String selectSql = SQLUtils.buildSelectSql(getClass());
		String querySql = Condition.buildConditionSqlNoLimit(paramMapList, selectSql);
		QueryData queryData = super.queryBySql(querySql, paramMapList);
		List<Map<String, Object>> queryMapList = queryData.getQueryBeanList();
		List<CoreModule> resultList = new ArrayList<CoreModule>();
		for (Map<String, Object> map : queryMapList) {
			CoreModule coreModule = (CoreModule) RKBeanUtil.convertMapToBean(map, CoreModule.class);
			resultList.add(coreModule);
		}
		return resultList;
	}
}
