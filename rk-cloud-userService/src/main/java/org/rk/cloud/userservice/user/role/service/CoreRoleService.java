package org.rk.cloud.userservice.user.role.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.role.dao.ICoreRoleDao;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.service.ICoreRoleService;
import org.rk.core.user.userRole.CoreRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreRoleService")
public class CoreRoleService extends ModelService<CoreRole> implements ICoreRoleService{
	private ICoreRoleDao modelDao;
	@Resource(name="CoreRoleDao")
	public void setCoreRoleDao(ICoreRoleDao modelDao) {
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
	public Long insertTreeModel(CoreRole coreRole){
		//获取当前最大的记录
		String maxId=String.valueOf(modelDao.queryMaxId(getPojoClass()));
		if(!RkStrUtil.hasText(coreRole.getFatherCode())){
			coreRole.setFatherCode("-1");//不指定父节点都是紧接着根节点
			coreRole.setCode(maxId+"A");
		}else{
			if(coreRole.getFatherCode().equals("-1")){
				coreRole.setCode(maxId+"A");
			}else{
				coreRole.setCode(coreRole.getFatherCode()+maxId+"A");
			}
		}
		return insertModel(coreRole);
	}
}
