package org.rk.cloud.userservice.module.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.module.dao.ICoreModuleDao;
import org.rk.core.common.util.RKAssert;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.domain.module.CoreModule;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreModuleService")
public class CoreModuleService extends ModelService<CoreModule> implements ICoreModuleService{
	
	private ICoreModuleDao modelDao;
	@Resource(name="CoreModuleDao")
	public void setCoreModuleDao(ICoreModuleDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Transactional
	@Override
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	@Transactional
	@Override
	public CoreModule selectByCode(String moduleCode){
		RKAssert.hasText(moduleCode, "模块代码不允许为空");
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("code", moduleCode);
		paramMapList.add(pm);
		try {
			return modelDao.queryForEntity(getPojoClass(), paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly=true)
	public List<CoreModule> selectByListByModuleCodes(List<String> moduleCodeList){
		List<CoreModule> result=modelDao.queryForListByModuleCodes(moduleCodeList);
		if(!RkObjectUtil.isEmpty(result)){
			return result;
		}
		return Collections.emptyList();
	}
	@Transactional
	@Override
	public CoreModule selectByUniqueId(String uniqueId){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("uniqueId", uniqueId);
		paramMapList.add(pm);
		try {
			return modelDao.queryForEntity(getPojoClass(), paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long insertModel(CoreModule model) {
		String maxId=String.valueOf(modelDao.queryMaxId(getPojoClass()));
		model.setUniqueId(maxId+"A");
		if(!RkStrUtil.hasText(model.getFatherUId())){
			model.setFatherUId("-1");
		}
		return super.insertModel(model);
	}
}
