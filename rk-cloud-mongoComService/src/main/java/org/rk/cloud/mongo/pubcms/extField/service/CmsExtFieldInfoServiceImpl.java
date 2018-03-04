/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 上午11:47:56
 */
package org.rk.cloud.mongo.pubcms.extField.service;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.cloud.mongo.pubcms.extField.dao.ICmsExtFieldInfoDao;
import org.rk.core.nosql.mongodb.base.BaseMongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cavion
 * @描述：
 */
@Service("cmsExtFieldInfoService")
public class CmsExtFieldInfoServiceImpl extends BaseMongoServiceImpl<CmsExtFieldInfo> implements ICmsExtFieldInfoService {

	private ICmsExtFieldInfoDao cmsExtFieldInfoDao;
	
	public ICmsExtFieldInfoDao getCmsExtFieldInfo() {
		return cmsExtFieldInfoDao;
	}
	@Autowired
	public void setCmsExtFieldInfo(ICmsExtFieldInfoDao cmsExtFieldInfo) {
		this.cmsExtFieldInfoDao = cmsExtFieldInfo;
		super.setBaseMogoDao(cmsExtFieldInfo);
	}

	/*@Override
	public void insert(CmsExtFieldInfo cmsExtFieldInfo) {
		cmsExtFieldInfoDao.insert(cmsExtFieldInfo);
	}*/
}
