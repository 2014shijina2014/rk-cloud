/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 上午11:01:14
 */
package org.rk.cloud.mongo.userExt.userExt.service;

import org.rk.cloud.mongo.userExt.userExt.UserExtInfo;
import org.rk.core.nosql.mongodb.base.IBaseMongoService;

/**
 * @author cavion
 * @描述：
 */
public interface IUserExtInfoService extends IBaseMongoService<UserExtInfo>{

	UserExtInfo findByMenbernNum(String menberNum);

}
