package org.rk.cloud.userservice.user.info.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.info.CoreUserInfo;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.constant.RkConst;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserInfoDao")
public class CoreUserInfoDao extends DBDao<CoreUserInfo> implements ICoreUserInfoDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserInfo model=new CoreUserInfo();
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
		sb.append("select userInfo.*,infokey.keyName,infokey.dataLength,infokey.dataType,infokey.selectType,infokey.selectCode,infokey.showIndex,infokey.isMust ");
		sb.append(" from core_user_info userInfo left join core_user_infokey infokey on infokey.keyCode=userInfo.keyCode ");
		sb.append(" where 1=1 and infokey.enabled='"+RkConst.yesno.yes+"' ");
		for(Map.Entry<String, Object> entry :mapObjs.entrySet()){
			sb.append(" and "+entry.getKey()+" =:"+entry.getKey()+" ");
			ParamMap pm=new ParamMap(entry.getKey(), entry.getValue());
			paramMapList.add(pm);
		}
		return sb;
	}
}
