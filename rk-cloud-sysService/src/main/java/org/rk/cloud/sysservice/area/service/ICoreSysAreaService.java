package org.rk.cloud.sysservice.area.service;

import org.rk.cloud.sysservice.area.CoreSysArea;
import org.rk.core.pubServer.service.IModelService;

public interface ICoreSysAreaService extends IModelService<CoreSysArea> {

	public boolean deleteById(Long id);

	Long insertTreeModel(CoreSysArea coreSysArea);
}
