package org.rk.cloud.userservice.user.baseInfo.service;

import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUserBaseService extends IModelService<CoreUserBase> {

	public boolean deleteById(Long id);
}
