package org.rk.cloud.userservice.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.domain.dao.ICoreDomainDao;
import org.rk.cloud.userservice.unit.unit.service.ICoreUnitService;
import org.rk.cloud.userservice.util.UserSysConst;
import org.rk.core.common.bean.PageData;
import org.rk.core.domain.domain.CoreDomain;
import org.rk.core.domain.service.ICoreDomainService;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.service.ICoreUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreDomainService")
public class CoreDomainService extends ModelService<CoreDomain> implements ICoreDomainService{
	@Resource(name = "CoreUserService")
	private ICoreUserService coreUserService;
	@Resource(name = "CoreUnitService")
	private ICoreUnitService coreUnitService;
	
	private ICoreDomainDao modelDao;
	@Resource(name="CoreDomainDao")
	public void setCoreDomainDao(ICoreDomainDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Transactional
	@Override
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	@Override
	@Transactional
	public PageData selectListPage(Map<String, Object> mapObjs, PageData page){
		page=super.selectModelListPage(mapObjs, page);
		List<Map<String,Object>> data=page.getData();
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : data) {
			String ownerType=(String) map.get("ownerType");
			Long owerId=(Long) map.get("ownerId");
			String ownerName="";
			if(UserSysConst.domainOwerType.owerType_user.equals(ownerType)){
				ownerName=coreUserService.selectModel(owerId).getUserName();
			}else{
				ownerName=coreUnitService.selectModel(owerId).getUnitName();
			}
			map.put("ownerName", ownerName);
			result.add(map);
		}
		page.setData(result);
		return page;
	}
}
