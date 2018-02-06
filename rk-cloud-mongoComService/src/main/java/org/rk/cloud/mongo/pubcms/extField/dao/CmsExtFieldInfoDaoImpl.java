/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:48:54
 */
package org.rk.cloud.mongo.pubcms.extField.dao;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.core.nosql.mongodb.base.BaseMongoDao;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：
 */
public class CmsExtFieldInfoDaoImpl extends BaseMongoDao<CmsExtFieldInfo> implements ICmsExtFieldInfoDao{
	/**
	 * 重写插入方法
	 */
	@Override
	public void insert(CmsExtFieldInfo cmsExtFieldInfo) {
		String collectionName = getMongoTemplate().getCollectionName(CmsExtFieldInfo.class);
		JSONObject jsonObj = cmsExtFieldInfo.getFieldInfoToJson();
		jsonObj.put("pubcmsId", cmsExtFieldInfo.getPubcmsId());
		jsonObj.put("createTime", cmsExtFieldInfo.getCreateTime());
		jsonObj.put("updateTime", cmsExtFieldInfo.getUpdateTime());
		jsonObj.put("id", cmsExtFieldInfo.getId());
		getMongoTemplate().insert(jsonObj, collectionName);
	}
}
