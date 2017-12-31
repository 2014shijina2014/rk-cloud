package org.rk.cloud.userservice.unit.info.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUnitInfoDao")
public class CoreUnitInfoDao extends DBDao<CoreUnitInfo> implements ICoreUnitInfoDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUnitInfo model=new CoreUnitInfo();
		model.setId(id);
		return super.deleteModel(model);
	}
	
	@Override
	public List<Map<String, Object>> queryJoinForList(Map<String,Object> mapObjs, List<OrderBean> orderList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		StringBuffer sb=genJoinSqlAndParam(mapObjs, paramMapList);
		paramMapList=ParamMap.addOrderToParamMap(paramMapList, orderList);
		return super.queryBySql(sb.toString(), paramMapList).getQueryBeanList();
	}
	
	@Override
	public PageData queryJoinForPage(Map<String,Object> mapObjs,PageData page, List<OrderBean> orderList){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		StringBuffer sb=genJoinSqlAndParam(mapObjs, paramMapList);
		paramMapList=ParamMap.addOrderToParamMap(paramMapList, orderList);
		return super.queryBySqlToPage(sb.toString(), paramMapList, page);
	}
	
	private StringBuffer genJoinSqlAndParam(Map<String,Object> mapObjs,List<ParamMap> paramMapList){
		StringBuffer sb=new StringBuffer();
		sb.append("select unitInfo.*,infokey.keyName,infokey.dataLength,infokey.dataType,infokey.selectType,infokey.selectCode,infokey.showIndex,infokey.isMust ");
		sb.append(" from core_unit_info unitInfo left join core_unit_infokey infokey on infokey.keyCode=unitInfo.keyCode ");
		sb.append(" where 1=1 and infokey.enabled='"+RkConst.yesno.yes+"' ");
		for(Map.Entry<String, Object> entry :mapObjs.entrySet()){
			String condiSql=Condition.conditionSql(entry.getKey(), entry.getValue());
			if(!RkStrUtil.hasText(condiSql)){
				continue;
			}
			sb.append(condiSql);
			ParamMap pm=new ParamMap(entry.getKey(), entry.getValue());
			paramMapList.add(pm);
		}
		return sb;
	}
}
