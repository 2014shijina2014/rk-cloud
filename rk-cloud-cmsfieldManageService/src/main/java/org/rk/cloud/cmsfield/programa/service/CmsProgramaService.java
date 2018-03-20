package org.rk.cloud.cmsfield.programa.service;

import javax.annotation.Resource;

import org.rk.cloud.cmsfield.programa.CmsPrograma;
import org.rk.cloud.cmsfield.programa.dao.ICmsProgramaDao;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CmsProgramaService")
public class CmsProgramaService extends ModelService<CmsPrograma> implements ICmsProgramaService{

	private ICmsProgramaDao modelDao;
	@Resource(name="CmsProgramaDao")
	public void setPubProgramaDao(ICmsProgramaDao modelDao) {
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
	public Long insertTreeModel(CmsPrograma pubPrograma){
		//获取当前最大的记录
		String maxId=String.valueOf(modelDao.queryMaxId(getPojoClass()));
		if(!RkStrUtil.hasText(pubPrograma.getFatherCode())){
			pubPrograma.setFatherCode("-1");//不指定父节点都是紧接着根节点
			pubPrograma.setCode(maxId+"A");
		}else{
			if(pubPrograma.getFatherCode().equals("-1")){
				pubPrograma.setCode(maxId+"A");
			}else{
				pubPrograma.setCode(pubPrograma.getFatherCode()+maxId+"A");
			}
		}
		return insertModel(pubPrograma);
	}
}
