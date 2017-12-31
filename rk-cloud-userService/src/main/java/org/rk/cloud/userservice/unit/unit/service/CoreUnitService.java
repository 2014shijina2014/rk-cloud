package org.rk.cloud.userservice.unit.unit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.cloud.userservice.unit.unit.dao.ICoreUnitDao;
import org.rk.cloud.userservice.unit.unitAdmin.service.ICoreUnitAdminService;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUnitService")
public class CoreUnitService extends ModelService<CoreUnit> implements ICoreUnitService{

	@Resource(name = "CoreUnitAdminService")
	private ICoreUnitAdminService coreUnitAdminService;
	
	private ICoreUnitDao modelDao;
	@Resource(name="CoreUnitDao")
	public void setCoreUnitDao(ICoreUnitDao modelDao) {
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
	public CoreUnit selectUnitByUserId(Long userId){
		List<Map<String, Object>> unitAdminList=coreUnitAdminService.selectListByUserId(userId);
		if(!RkObjectUtil.isEmpty(unitAdminList)&&unitAdminList.size()>0){
			Long unitId=(Long) unitAdminList.get(0).get("unitId");
			return super.selectModel(unitId);
		}else{
			return null;
		}
	}
}
