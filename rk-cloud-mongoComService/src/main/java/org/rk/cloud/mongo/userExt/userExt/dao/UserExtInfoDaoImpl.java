/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:48:54
 */
package org.rk.cloud.mongo.userExt.userExt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.rk.cloud.mongo.userExt.userExt.UserExtInfo;
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
@Repository("userExtInfoDao")
public class UserExtInfoDaoImpl extends BaseMongoDao<UserExtInfo> implements IUserExtInfoDao{
	public static String collectionName;
	@PostConstruct
	public void init() {
		getCollectionName();
	}
	
	public String getCollectionName() {
		if(RkStrUtil.isEmpty(collectionName)) {
			collectionName = getMongoTemplate().getCollectionName(UserExtInfo.class);
		}
		return collectionName;
	}
	/**
	 * 重写插入方法
	 */
	@Override
	public void insert(UserExtInfo cmsExtFieldInfo) {
		JSONObject jsonObj = cmsExtFieldInfo.getUserExtInfoJson();
		jsonObj.put("menberNumber", cmsExtFieldInfo.getMenberNumber());
		jsonObj.put("createTime", cmsExtFieldInfo.getCreateTime());
		jsonObj.put("updateTime", cmsExtFieldInfo.getUpdateTime());
		getMongoTemplate().insert(jsonObj, getCollectionName());
	}
	/**
	 * 重写find方法
	 */
	@Override
	public List<UserExtInfo> find(Query query){
		List<JSONObject> queryResult = execQueryCmd(query, getCollectionName());
		List<UserExtInfo> cmsExtFieldInfoList = new ArrayList<UserExtInfo>();
		for (JSONObject jsonObj : queryResult) {
			UserExtInfo userExtInfo = new UserExtInfo();
			Date createTime = jsonObj.getDate("createTime");
			if(!RkObjectUtil.isEmpty(createTime)) {
				userExtInfo.setCreateTime(createTime.getTime());
			}
			Date updateTime = jsonObj.getDate("updateTime");
			if(!RkObjectUtil.isEmpty(updateTime)) {
				userExtInfo.setUpdateTime(updateTime.getTime());
			}
			userExtInfo.setUserExtInfoJson(jsonObj);
			userExtInfo.setMenberNumber(jsonObj.getString("menberNumber"));
			userExtInfo.setId(jsonObj.getString("_id"));
			cmsExtFieldInfoList.add(userExtInfo);
		}
		return cmsExtFieldInfoList;
	}
}
