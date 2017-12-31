package org.rk.cloud.userservice.user.userAddress.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
import org.rk.cloud.userservice.user.userAddress.dao.ICoreUserAddressDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserAddressService")
public class CoreUserAddressService extends ModelService<CoreUserAddress> implements ICoreUserAddressService{

	private ICoreUserAddressDao modelDao;
	@Resource(name="CoreUserAddressDao")
	public void setCoreUserAddressDao(ICoreUserAddressDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
