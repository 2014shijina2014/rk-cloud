package org.rk.cloud.sysservice.param.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.cloud.sysservice.param.dao.ICoreSysParamDao;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreSysParamService")
public class CoreSysParamService extends ModelService<CoreSysParam> implements ICoreSysParamService{

	private ICoreSysParamDao modelDao;
	@Resource(name="CoreSysParamDao")
	public void setCoreSysParamDao(ICoreSysParamDao modelDao) {
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
	public List<CoreSysParam> selectByCodePrefix(String codePrefix){
		Map<String,Object> mapObjs=new HashMap<String,Object>();
		mapObjs.put("code", codePrefix+"%");
		List<CoreSysParam> paramList=selectModelList(mapObjs);
		if(RkObjectUtil.isEmpty(paramList)||paramList.size()<1){
			return Collections.emptyList();
		}
		return paramList;
	}
}
