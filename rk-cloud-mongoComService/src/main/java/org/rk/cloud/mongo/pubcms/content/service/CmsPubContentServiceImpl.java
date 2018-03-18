/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月18日 下午8:30:05
 */
package org.rk.cloud.mongo.pubcms.content.service;

import java.util.List;

import org.rk.cloud.mongo.pubcms.content.CmsPubContent;
import org.rk.cloud.mongo.pubcms.content.dao.ICmsPubContentDao;
import org.rk.core.common.exception.RkMsgException;
import org.rk.core.nosql.mongodb.base.BaseMongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author cavion
 * @描述：
 */
@Service("cmsPubContentService")
public class CmsPubContentServiceImpl extends BaseMongoServiceImpl<CmsPubContent> implements ICmsPubContentService {
	private ICmsPubContentDao cmsPubContentDao;
	
	public ICmsPubContentDao getCmsPubContentDao() {
		return cmsPubContentDao;
	}
	@Autowired
	public void setCmsPubContentDao(ICmsPubContentDao cmsPubContentDao) {
		this.cmsPubContentDao = cmsPubContentDao;
		super.setBaseMogoDao(cmsPubContentDao);
	}
	
	@Override
	public CmsPubContent findByPubcmsId(String pubcmsId) {
		List<CmsPubContent> cmsPubContentList = findByProperty("pubcmsId", pubcmsId);
		if(CollectionUtils.isEmpty(cmsPubContentList)) {
			return null;
		}
		if(cmsPubContentList.size()>1) {
			throw new RkMsgException("查询总数大于1，请联系运维检查数据");
		}
		return cmsPubContentList.get(0);
	}
}
