package org.rk.cloud.userservice.unit.unitAdminPerm.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.cloud.userservice.unit.unitAdminPerm.dao.ICoreUnitAdminPermDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUnitAdminPermService")
public class CoreUnitAdminPermService extends ModelService<CoreUnitAdminPerm> implements ICoreUnitAdminPermService{

	private ICoreUnitAdminPermDao modelDao;
	@Resource(name="CoreUnitAdminPermDao")
	public void setCoreUnitAdminPermDao(ICoreUnitAdminPermDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
