package org.rk.cloud.userservice.unit.unitAdmin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdmin.CoreUnitAdmin;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUnitAdminDao")
public class CoreUnitAdminDao extends DBDao<CoreUnitAdmin> implements ICoreUnitAdminDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUnitAdmin model=new CoreUnitAdmin();
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
		if(RkObjectUtil.isEmpty(orderList)||orderList.size()==0){
			paramMapList=ParamMap.addPageToParamMap(paramMapList, page);
		}else{
			paramMapList=ParamMap.addPageAndOrderToParamMap(paramMapList, page, orderList);
		}
		return super.queryBySqlToPage(sb.toString(), paramMapList, page);
	}
	
	private StringBuffer genJoinSqlAndParam(Map<String,Object> mapObjs,List<ParamMap> paramMapList){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT cua.id,cua.unitId,cua.userId,cua.isOwner,cua.enabled,cua.createTime,cua.creator,cua.updateTime,cua.updator,");
		sb.append("unit.unitName,unit.address,unit.email as unitEmail,unit.telphone,unit.unitType,unit.unitIntro,unit.unitDetail,unit.isActive,unit.activeDate,");
		sb.append(" user.userName,user.mobilephone,user.email as userEmail,user.registerTime,user.fromDomain,user.userType ");
		sb.append(" FROM  core_unit_admin cua  ");
		sb.append(" INNER JOIN core_unit unit ON unit.id=cua.unitId  ");
		sb.append(" INNER JOIN core_user user ON user.id=cua.userId  ");
		sb.append(" where 1=1 ");
		for(Map.Entry<String, Object> entry :mapObjs.entrySet()){
			Object paramValue=entry.getValue();
			String condiSql=Condition.conditionSql(entry.getKey(), paramValue);
			if(!RkStrUtil.hasText(condiSql)){
				continue;
			}
			sb.append(condiSql);
			ParamMap pm=new ParamMap(entry.getKey(), paramValue);
			paramMapList.add(pm);
		}
		return sb;
	}
}
