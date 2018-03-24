/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 上午11:47:56
 */
package org.rk.cloud.mongo.userExt.userExt.service;

import java.util.List;

import org.rk.cloud.mongo.userExt.userExt.UserExtInfo;
import org.rk.cloud.mongo.userExt.userExt.dao.IUserExtInfoDao;
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
@Service("userExtInfoService")
public class UserExtInfoServiceImpl extends BaseMongoServiceImpl<UserExtInfo> implements IUserExtInfoService {

	private IUserExtInfoDao userExtInfoDaoDao;
	
	public IUserExtInfoDao getUserExtInfoDaoDao() {
		return userExtInfoDaoDao;
	}
	@Autowired
	public void setUserExtInfoDaoDao(IUserExtInfoDao userExtInfoDaoDao) {
		this.userExtInfoDaoDao = userExtInfoDaoDao;
		super.setBaseMogoDao(userExtInfoDaoDao);
	}
	@Override
	public UserExtInfo findByMenbernNum(String menberNum) {
		Query query = new Query(Criteria.where("menberNumber").is(menberNum));
		List<UserExtInfo> userExtInfoist = userExtInfoDaoDao.find(query);
		if(CollectionUtils.isEmpty(userExtInfoist)) {
			return null;
		}
		if(userExtInfoist.size()>1) {
			throw new RkMsgException("查询总数大于1，请联系运维检查数据");
		}
		return userExtInfoist.get(0);
	}
}
