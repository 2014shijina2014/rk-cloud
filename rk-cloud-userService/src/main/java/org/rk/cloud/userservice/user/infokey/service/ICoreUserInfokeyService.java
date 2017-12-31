package org.rk.cloud.userservice.user.infokey.service;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUserInfokeyService extends IModelService<CoreUserInfokey> {

	public boolean deleteById(Long id);

	CoreUserInfokey selectByCode(String keyCode);
}
