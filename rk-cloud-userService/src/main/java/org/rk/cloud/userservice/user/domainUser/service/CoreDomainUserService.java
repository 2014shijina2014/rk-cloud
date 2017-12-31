package org.rk.cloud.userservice.user.domainUser.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.domainUser.CoreDomainUser;
import org.rk.cloud.userservice.user.domainUser.dao.ICoreDomainUserDao;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreDomainUserService")
public class CoreDomainUserService extends ModelService<CoreDomainUser> implements ICoreDomainUserService{

	private ICoreDomainUserDao modelDao;
	@Resource(name="CoreDomainUserDao")
	public void setCoreDomainUserDao(ICoreDomainUserDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public PageData selectListPage(Map<String, Object> mapObjs, PageData page){
		page=modelDao.queryJoinForPage(mapObjs, page, null);
		return page;
	}
	@Override
	@Transactional
	public List<Map<String, Object>> selectList(Map<String, Object> mapObjs){
		return modelDao.queryJoinForList(mapObjs, null);
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>>  selectListByUserId(Long userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainUser.userId", userId);
		List<Map<String, Object>> result=selectList(params);
		if(RkObjectUtil.isEmpty(result)||result.size()<1){
			return Collections.emptyList();
		}
		return result;
	}
	@Override
	@Transactional
	public PageData  selectListPageByUserId(Long userId, PageData page){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainUser.userId", userId);
		page=selectListPage(params, page);
		return page;
	}
	@Override
	@Transactional
	public List<Map<String, Object>> selectListByDomainId(Long domainId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainUser.domainId", domainId);
		List<Map<String, Object>> result=selectList(params);
		if(RkObjectUtil.isEmpty(result)||result.size()<1){
			return Collections.emptyList();
		}
		return result;
	}
	@Override
	@Transactional
	public PageData selectListPageByDomainId(Long domainId, PageData page){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainUser.domainId", domainId);
		page=selectListPage(params, page);
		return page;
	}
	
	@Override
	@Transactional
	public Map<String, Object>  selectMapByUserIdAndDomainId(Long userId,Long domainId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainUser.userId", userId);
		params.put("domainUser.domainId", domainId);
		List<Map<String, Object>> result=selectList(params);
		if(RkObjectUtil.isEmpty(result)||result.size()<1){
			return new HashMap<String, Object>();
		}
		return result.get(0);
	}
}
