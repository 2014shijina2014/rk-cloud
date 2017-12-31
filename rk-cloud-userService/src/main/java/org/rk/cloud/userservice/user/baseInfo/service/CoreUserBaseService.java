package org.rk.cloud.userservice.user.baseInfo.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.rk.cloud.userservice.user.baseInfo.dao.ICoreUserBaseDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserBaseService")
public class CoreUserBaseService extends ModelService<CoreUserBase> implements ICoreUserBaseService{

	private ICoreUserBaseDao modelDao;
	@Resource(name="CoreUserBaseDao")
	public void setCoreUserBaseDao(ICoreUserBaseDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
