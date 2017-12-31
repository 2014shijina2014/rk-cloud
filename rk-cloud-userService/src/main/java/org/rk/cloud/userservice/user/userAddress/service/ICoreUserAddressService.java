package org.rk.cloud.userservice.user.userAddress.service;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUserAddressService extends IModelService<CoreUserAddress> {

	public boolean deleteById(Long id);
}
