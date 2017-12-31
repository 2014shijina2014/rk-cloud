package org.rk.cloud.userservice.user.userPerm.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.userPerm.dao.ICoreUserPermDao;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.service.ICoreUserPermService;
import org.rk.core.user.userPerm.CoreUserPerm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserPermService")
public class CoreUserPermService extends ModelService<CoreUserPerm> implements ICoreUserPermService{

	private ICoreUserPermDao modelDao;
	@Resource(name="CoreUserPermDao")
	public void setCoreUserPermDao(ICoreUserPermDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
