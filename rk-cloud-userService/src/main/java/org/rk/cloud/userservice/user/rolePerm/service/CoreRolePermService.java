package org.rk.cloud.userservice.user.rolePerm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.rolePerm.dao.ICoreRolePermDao;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.rolePerm.CoreRolePerm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreRolePermService")
public class CoreRolePermService extends ModelService<CoreRolePerm> implements ICoreRolePermService{
	
	private ICoreRolePermDao modelDao;
	@Resource(name="CoreRolePermDao")
	public void setCoreRolePermDao(ICoreRolePermDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Transactional
	@Override
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<CoreRolePerm> selectByRoleId(long roleId){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("roleId", roleId);
		paramMapList.add(pm);
		List<CoreRolePerm> result=modelDao.queryForList(getPojoClass(), paramMapList);
		if(!RkObjectUtil.isEmpty(result)){
			return result;
		}
		return Collections.emptyList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CoreRolePerm> selectByListByRoleIds(List<Long> roleIdList){
		List<CoreRolePerm> result=modelDao.queryForListByRoleIds(roleIdList);
		if(!RkObjectUtil.isEmpty(result)){
			return result;
		}
		return Collections.emptyList();
	}
	
	@Override
	@Transactional
	public Long inserRolePerm(CoreRolePerm coreRolePerm){
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pmRoleId=new ParamMap("roleId", coreRolePerm.getRoleId());
		ParamMap pmPermId=new ParamMap("permId", coreRolePerm.getPermId());
		paramMapList.add(pmRoleId);
		paramMapList.add(pmPermId);
		List<CoreRolePerm> result=modelDao.queryForList(getPojoClass(), paramMapList);
		if(!RkObjectUtil.isEmpty(result)){
			RKAlert.Error("该角色已获得该权限");;
		}
		return insertModel(coreRolePerm);
	}
}
