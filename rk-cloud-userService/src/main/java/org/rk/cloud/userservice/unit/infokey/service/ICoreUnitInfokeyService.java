package org.rk.cloud.userservice.unit.infokey.service;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUnitInfokeyService extends IModelService<CoreUnitInfokey> {

	public boolean deleteById(Long id);

	CoreUnitInfokey selectByCode(String keyCode);
}
