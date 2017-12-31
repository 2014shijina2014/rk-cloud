package org.rk.cloud.userservice.unit.unitAdminPerm.service;

import org.rk.cloud.userservice.unit.unitAdminPerm.CoreUnitAdminPerm;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUnitAdminPermService extends IModelService<CoreUnitAdminPerm> {

	public boolean deleteById(Long id);
}
