package org.rk.cloud.field.cms.progProp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.jdbc.dao.DBDao;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CmsProgPropDao")
public class CmsProgPropDao extends DBDao<CmsProgProp> implements ICmsProgPropDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CmsProgProp model=new CmsProgProp();
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
	private List<String> programaCodeList(String paramValue){
		String paramValueNoA=paramValue.substring(0, paramValue.lastIndexOf("A"));
		int paramLength=paramValue.split("A").length;
		List<String> paramValueList=new ArrayList<String>();
		for (int i = 0; i < paramLength; i++) {
			if(i==0){
				paramValueList.add(paramValue);
			}else{
				paramValueNoA=paramValueNoA.substring(0, paramValueNoA.lastIndexOf("A"));
				String currParam=paramValueNoA;
				if(!currParam.endsWith("A")){
					currParam=paramValueNoA+"A";
				}
				paramValueList.add(currParam);
			}
		}
		return paramValueList;
	}
	private StringBuffer genJoinSqlAndParam(Map<String,Object> mapObjs,List<ParamMap> paramMapList){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT mpp.id,mpp.programaCode,mpp.propertyCode,mpp.enabled,mpp.remark,mpp.createTime,mpp.creator,mpp.orderNum,mpp.isMust,");
		sb.append(" pp.name as programaName ,pp.enName as programaEnName ,mp.name as propertyName ,mp.enName as propertyEnName");
		sb.append(" FROM  cms_prog_prop mpp  ");
		sb.append(" INNER JOIN cms_programa pp ON pp.code=mpp.programaCode  ");
		sb.append(" INNER JOIN cms_property mp ON mp.code=mpp.propertyCode  ");
		sb.append(" where 1=1 and pp.enabled='"+RkConst.yesno.yes+"' ");
		for(Map.Entry<String, Object> entry :mapObjs.entrySet()){
			String condiSql="";
			Object paramValue=entry.getValue();
			if(entry.getKey().equals("programaCode")){
				String paramValueStr=String.valueOf(paramValue);
				List<String> paramValueList=programaCodeList(paramValueStr);
				paramValue=paramValueList;
				condiSql=Condition.conditionSql(entry.getKey(), paramValueList,Condition.condi_mode_eq_in);
			}else{
				condiSql=Condition.conditionSql(entry.getKey(), entry.getValue());
			}
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
