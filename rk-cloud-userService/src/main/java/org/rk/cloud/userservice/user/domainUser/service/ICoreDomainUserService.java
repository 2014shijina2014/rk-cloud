package org.rk.cloud.userservice.user.domainUser.service;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.user.domainUser.CoreDomainUser;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreDomainUserService extends IModelService<CoreDomainUser> {

	public boolean deleteById(Long id);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);

	List<Map<String, Object>> selectList(Map<String, Object> mapObjs);

	List<Map<String, Object>> selectListByUserId(Long userId);

	List<Map<String, Object>> selectListByDomainId(Long domainId);

	Map<String, Object> selectMapByUserIdAndDomainId(Long userId, Long domainId);

	PageData selectListPageByUserId(Long userId, PageData page);

	PageData selectListPageByDomainId(Long domainId, PageData page);
}
