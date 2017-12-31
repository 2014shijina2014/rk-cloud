package org.rk.cloud.userservice.unit.info.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.cloud.userservice.unit.info.dao.ICoreUnitInfoDao;
import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.cloud.userservice.unit.unit.service.ICoreUnitService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUnitInfoService")
public class CoreUnitInfoService extends ModelService<CoreUnitInfo> implements ICoreUnitInfoService{

	@Resource(name = "CoreUnitService")
	private ICoreUnitService coreUnitService;
	
	private ICoreUnitInfoDao modelDao;
	@Resource(name="CoreUnitInfoDao")
	public void setCoreUnitInfoDao(ICoreUnitInfoDao modelDao) {
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
		page=coreUnitService.selectModelListPage(mapObjs, page);
		List<Map<String,Object>> dataList=page.getData();
		List<Map<String,Object>> newdataList=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : dataList) {
			Long unitId=(Long) map.get("id");
			List<Map<String, Object>> coreUnitInfoList =selectListByUnitId(unitId);
			for (Map<String, Object> coreUnitInfo : coreUnitInfoList) {
				map.put((String)coreUnitInfo.get("keyCode"), coreUnitInfo.get("infoValue"));
			}
			newdataList.add(map);
		}
		page.setData(newdataList);
		return page;
	}
	
	@Override
	@Transactional
	public Map<String,Object> selectUnitInfo(Long unitId){
		Map<String,Object> unitInfoMap=new HashMap<String, Object>();
		CoreUnit coreUnit=coreUnitService.selectModel(unitId);
		unitInfoMap=RKBeanUtil.convertBeanToMap(coreUnit);//基础信息
		//扩展信息
		List<Map<String, Object>> coreUnitInfoList =selectListByUnitId(unitId);
		for (Map<String, Object> coreUnitInfo : coreUnitInfoList) {
			unitInfoMap.put((String)coreUnitInfo.get("keyCode"), coreUnitInfo.get("infoValue"));
		}
		return unitInfoMap;
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> selectListByUnitId(Long unitId){
		Map<String,Object> mapObjs=new HashMap<String, Object>();
		mapObjs.put("unitId", unitId);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("showIndex", Condition.condi_mode_order_asc);
		orderList.add(orderMap);
		List<Map<String, Object>> resultList =modelDao.queryJoinForList(mapObjs, orderList);
		return resultList;
	}
	
	@Override
	@Transactional
	public void updateUnitInfo(CoreUnitInfo coreUnitInfo){
		if(RkObjectUtil.isEmpty(coreUnitInfo.getId())){//如果id不存在，检测unitId和keyCode，同一个unitId和keyCode只有一条记录
			Map<String,Object> mapObjs=new HashMap<String, Object>();
			mapObjs.put("unitId", coreUnitInfo.getUnitId());
			mapObjs.put("keyCode", coreUnitInfo.getKeyCode());
			List<CoreUnitInfo> result=super.selectModelList(mapObjs);
			if(!RkObjectUtil.isEmpty(result)&&result.size()>0){
				coreUnitInfo.setId(result.get(0).getId());
				super.updateModel(coreUnitInfo);
			}else{
				super.insertModel(coreUnitInfo);
			}
		}
		super.updateModel(coreUnitInfo);
	}
}
