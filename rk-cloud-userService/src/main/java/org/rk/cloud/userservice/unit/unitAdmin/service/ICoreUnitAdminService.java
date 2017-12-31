package org.rk.cloud.userservice.unit.unitAdmin.service;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.unit.unitAdmin.CoreUnitAdmin;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUnitAdminService extends IModelService<CoreUnitAdmin> {

	public boolean deleteById(Long id);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);

	List<Map<String, Object>> selectList(Map<String, Object> mapObjs);

	List<Map<String, Object>> selectListByUserId(Long userId);

	List<Map<String, Object>> selectListByUnitId(Long unitId);
}
