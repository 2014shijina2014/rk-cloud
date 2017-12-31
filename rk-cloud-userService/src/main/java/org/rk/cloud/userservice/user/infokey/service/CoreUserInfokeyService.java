package org.rk.cloud.userservice.user.infokey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.cloud.userservice.user.infokey.dao.ICoreUserInfokeyDao;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserInfokeyService")
public class CoreUserInfokeyService extends ModelService<CoreUserInfokey> implements ICoreUserInfokeyService{

	private ICoreUserInfokeyDao modelDao;
	@Resource(name="CoreUserInfokeyDao")
	public void setCoreUserInfokeyDao(ICoreUserInfokeyDao modelDao) {
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
	public CoreUserInfokey selectByCode(String keyCode){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("keyCode", keyCode);
		paramMapList.add(pm);
		try {
			return modelDao.queryForEntity(getPojoClass(), paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
