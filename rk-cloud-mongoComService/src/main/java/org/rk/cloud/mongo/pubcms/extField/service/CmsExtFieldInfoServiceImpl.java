/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 上午11:47:56
 */
package org.rk.cloud.mongo.pubcms.extField.service;

import java.util.List;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.cloud.mongo.pubcms.extField.dao.ICmsExtFieldInfoDao;
import org.rk.core.common.exception.RkMsgException;
import org.rk.core.nosql.mongodb.base.BaseMongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
	@Override
	public CmsExtFieldInfo findByPubcmsId(String pubcmsId) {
		Query query = new Query(Criteria.where("pubcmsId").is(pubcmsId));
		List<CmsExtFieldInfo> cmsExtFieldist = cmsExtFieldInfoDao.find(query);
		if(CollectionUtils.isEmpty(cmsExtFieldist)) {
			return null;
		}
		if(cmsExtFieldist.size()>1) {
			throw new RkMsgException("查询总数大于1，请联系运维检查数据");
		}
		return cmsExtFieldist.get(0);
	}
}
