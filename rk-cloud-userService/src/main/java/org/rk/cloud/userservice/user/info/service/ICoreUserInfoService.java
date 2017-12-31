package org.rk.cloud.userservice.user.info.service;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.user.info.CoreUserInfo;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUserInfoService extends IModelService<CoreUserInfo> {

	public boolean deleteById(Long id);

	Map<String,Object> selectUserInfo(Long userId);

	List<Map<String, Object>> selectListByUserId(Long userId);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);

	void updateUserInfo(CoreUserInfo coreUserInfo);
}
