package org.rk.cloud.userservice.unit.unit.service;

import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUnitService extends IModelService<CoreUnit> {

	public boolean deleteById(Long id);

	CoreUnit selectUnitByUserId(Long userId);
}
