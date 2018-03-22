package org.rk.cloud.field.cms.property.service;

import javax.annotation.Resource;

import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.cloud.field.cms.property.dao.ICmsPropertyDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CmsPropertyService")
public class CmsPropertyService extends ModelService<CmsProperty> implements ICmsPropertyService{

	private ICmsPropertyDao modelDao;
	@Resource(name="CmsPropertyDao")
	public void setMallPropertyDao(ICmsPropertyDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
