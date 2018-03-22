package org.rk.cloud.field.cms.progProp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.cloud.field.cms.progProp.dao.ICmsProgPropDao;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CmsProgPropService")
public class CmsProgPropService extends ModelService<CmsProgProp> implements ICmsProgPropService{

	private ICmsProgPropDao modelDao;
	@Resource(name="CmsProgPropDao")
	public void setMallProgramaPropertyDao(ICmsProgPropDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public PageData selectListPage(Map<String, Object> mapObjs, PageData page){
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderBean=new OrderBean("orderNum",Condition.condi_mode_order_asc);
		orderList.add(orderBean);
		page=modelDao.queryJoinForPage(mapObjs, page,orderList);
		return page;
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> selectList(Map<String, Object> mapObjs){
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderBean=new OrderBean("orderNum",Condition.condi_mode_order_asc);
		orderList.add(orderBean);
		List<Map<String, Object>> result=modelDao.queryJoinForList(mapObjs, orderList);
		if(!RkObjectUtil.isEmpty(result)&&result.size()>0){
			return result;
		}
		return Collections.emptyList();
	}
}
