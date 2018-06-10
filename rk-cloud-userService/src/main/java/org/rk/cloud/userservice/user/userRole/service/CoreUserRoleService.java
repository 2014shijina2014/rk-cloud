package org.rk.cloud.userservice.user.userRole.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.userRole.dao.ICoreUserRoleDao;
import org.rk.core.common.util.RKAlert;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.userRole.CoreUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserRoleService")
public class CoreUserRoleService extends ModelService<CoreUserRole> implements ICoreUserRoleService{
	private ICoreUserRoleDao modelDao;
	@Resource(name="CoreUserRoleDao")
	public void setCoreUserRoleDao(ICoreUserRoleDao modelDao) {
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
	public List<CoreUserRole> selectByUserNum(String userNum){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("userNum", userNum);
		paramMapList.add(pm);
		try {
			return modelDao.queryForList(getPojoClass(), paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RKAlert.Error("发生异常！");
		}
		return Collections.emptyList();
	}
}
