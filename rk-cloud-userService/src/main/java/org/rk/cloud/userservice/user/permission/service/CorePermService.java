package org.rk.cloud.userservice.user.permission.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.permission.dao.ICorePermDao;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.service.ICorePermService;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CorePermService")
public class CorePermService extends ModelService<CorePerm> implements ICorePermService{
	
	private ICorePermDao modelDao;
	@Resource(name="CorePermDao")
	public void setCorePermDao(ICorePermDao modelDao) {
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
	public CorePerm selectByCode(String permCode){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("permCode", permCode);
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
