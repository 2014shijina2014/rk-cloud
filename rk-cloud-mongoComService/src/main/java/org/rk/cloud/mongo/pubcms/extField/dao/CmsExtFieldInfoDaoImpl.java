/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:48:54
 */
package org.rk.cloud.mongo.pubcms.extField.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.nosql.mongodb.base.BaseMongoDao;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：
 */
@Repository("CmsExtFieldInfoDao")
public class CmsExtFieldInfoDaoImpl extends BaseMongoDao<CmsExtFieldInfo> implements ICmsExtFieldInfoDao{
	public static String collectionName;
	@PostConstruct
	public void init() {
		getCollectionName();
	}
	
	public String getCollectionName() {
		if(RkStrUtil.isEmpty(collectionName)) {
			collectionName = getMongoTemplate().getCollectionName(CmsExtFieldInfo.class);
		}
		return collectionName;
	}
	/**
	 * 重写插入方法
	 */
	@Override
	public void insert(CmsExtFieldInfo cmsExtFieldInfo) {
		JSONObject jsonObj = cmsExtFieldInfo.getFieldInfoToJson();
		jsonObj.put("pubcmsId", cmsExtFieldInfo.getPubcmsId());
		jsonObj.put("createTime", cmsExtFieldInfo.getCreateTime());
		jsonObj.put("updateTime", cmsExtFieldInfo.getUpdateTime());
		getMongoTemplate().insert(jsonObj, getCollectionName());
	}
	/**
	 * 重写find方法
	 */
	@Override
	public List<CmsExtFieldInfo> find(Query query){
		List<JSONObject> queryResult = execQueryCmd(query, getCollectionName());
		List<CmsExtFieldInfo> cmsExtFieldInfoList = new ArrayList<CmsExtFieldInfo>();
		for (JSONObject jsonObj : queryResult) {
			CmsExtFieldInfo cmsExtFieldInfo = new CmsExtFieldInfo();
			Date createTime = jsonObj.getDate("createTime");
			if(!RkObjectUtil.isEmpty(createTime)) {
				cmsExtFieldInfo.setCreateTime(createTime.getTime());
			}
			Date updateTime = jsonObj.getDate("updateTime");
			if(!RkObjectUtil.isEmpty(updateTime)) {
				cmsExtFieldInfo.setUpdateTime(updateTime.getTime());
			}
			cmsExtFieldInfo.setFieldInfoToJson(jsonObj);
			cmsExtFieldInfo.setPubcmsId(jsonObj.getString("pubcmsId"));
			cmsExtFieldInfo.setId(jsonObj.getString("_id"));
			cmsExtFieldInfoList.add(cmsExtFieldInfo);
		}
		return cmsExtFieldInfoList;
	}
}
