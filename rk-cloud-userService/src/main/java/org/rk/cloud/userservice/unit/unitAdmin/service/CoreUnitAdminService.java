package org.rk.cloud.userservice.unit.unitAdmin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdmin.CoreUnitAdmin;
import org.rk.cloud.userservice.unit.unitAdmin.dao.ICoreUnitAdminDao;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUnitAdminService")
public class CoreUnitAdminService extends ModelService<CoreUnitAdmin> implements ICoreUnitAdminService{

	private ICoreUnitAdminDao modelDao;
	@Resource(name="CoreUnitAdminDao")
	public void setCoreUnitAdminDao(ICoreUnitAdminDao modelDao) {
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
		OrderBean orderBean=new OrderBean("isOwner",Condition.condi_mode_order_asc);
		orderList.add(orderBean);
		page=modelDao.queryJoinForPage(mapObjs, page,orderList);
		return page;
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> selectList(Map<String, Object> mapObjs){
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderBean=new OrderBean("isOwner",Condition.condi_mode_order_asc);
		orderList.add(orderBean);
		List<Map<String, Object>> result=modelDao.queryJoinForList(mapObjs, orderList);
		return result;
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> selectListByUserId(Long userId){
		Map<String, Object> mapObjs=new HashMap<String, Object>();
		mapObjs.put("userId", userId);
		List<Map<String, Object>> result=selectList(mapObjs);
		return result;
	}
	@Override
	@Transactional
	public List<Map<String, Object>> selectListByUnitId(Long unitId){
		Map<String, Object> mapObjs=new HashMap<String, Object>();
		mapObjs.put("unitId", unitId);
		List<Map<String, Object>> result=selectList(mapObjs);
		return result;
	}
}
