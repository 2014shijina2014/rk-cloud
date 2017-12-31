package org.rk.cloud.sysservice.param.service;

import java.util.List;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreSysParamService extends IModelService<CoreSysParam> {

	public boolean deleteById(Long id);

	List<CoreSysParam> selectByCodePrefix(String codePrefix);
}
