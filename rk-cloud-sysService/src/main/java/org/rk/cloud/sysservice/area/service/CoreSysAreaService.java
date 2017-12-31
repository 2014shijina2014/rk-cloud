package org.rk.cloud.sysservice.area.service;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.area.CoreSysArea;
import org.rk.cloud.sysservice.area.dao.ICoreSysAreaDao;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreSysAreaService")
public class CoreSysAreaService extends ModelService<CoreSysArea> implements ICoreSysAreaService{

	private ICoreSysAreaDao modelDao;
	@Resource(name="CoreSysAreaDao")
	public void setCoreSysAreaDao(ICoreSysAreaDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		CoreSysArea coreSysArea=selectModel(id);
		if(coreSysArea.getCode().equals("-1")){
			RKAlert.Error("根节点不允许删除");
			return false;
		}
		return modelDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public Long insertTreeModel(CoreSysArea coreSysArea){
		//获取当前最大的记录
		String maxId=String.valueOf(modelDao.queryMaxId(getPojoClass()));
		if(!RkStrUtil.hasText(coreSysArea.getFatherCode())){
			coreSysArea.setFatherCode("-1");//不指定父节点都是紧接着根节点
			coreSysArea.setCode(maxId+"A");
		}else{
			if(coreSysArea.getFatherCode().equals("-1")){
				coreSysArea.setCode(maxId+"A");
			}else{
				coreSysArea.setCode(coreSysArea.getFatherCode()+maxId+"A");
			}
		}
		return insertModel(coreSysArea);
	}
}
