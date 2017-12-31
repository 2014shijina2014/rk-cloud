package org.rk.cloud.userservice.unit.infokey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.cloud.userservice.unit.infokey.dao.ICoreUnitInfokeyDao;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUnitInfokeyService")
public class CoreUnitInfokeyService extends ModelService<CoreUnitInfokey> implements ICoreUnitInfokeyService{

	private ICoreUnitInfokeyDao modelDao;
	@Resource(name="CoreUnitInfokeyDao")
	public void setCoreUnitInfokeyDao(ICoreUnitInfokeyDao modelDao) {
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
	public CoreUnitInfokey selectByCode(String keyCode){
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
