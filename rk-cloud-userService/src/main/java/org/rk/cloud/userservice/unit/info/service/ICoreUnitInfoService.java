package org.rk.cloud.userservice.unit.info.service;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.service.IModelService;



public interface ICoreUnitInfoService extends IModelService<CoreUnitInfo> {

	public boolean deleteById(Long id);

	void updateUnitInfo(CoreUnitInfo coreUnitInfo);

	List<Map<String, Object>> selectListByUnitId(Long unitId);

	Map<String,Object> selectUnitInfo(Long unitId);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);
}
