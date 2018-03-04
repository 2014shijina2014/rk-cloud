/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:48:54
 */
package org.rk.cloud.mongo.pubcms.content.dao;

import org.rk.cloud.mongo.pubcms.content.CmsPubContent;
import org.rk.core.nosql.mongodb.base.BaseMongoDao;
import org.springframework.stereotype.Repository;

/**
 * @author cavion
 * @描述：
 */
@Repository("cmsPubContentDao")
public class CmsPubContentDaoImpl extends BaseMongoDao<CmsPubContent> implements ICmsPubContentDao{

}
