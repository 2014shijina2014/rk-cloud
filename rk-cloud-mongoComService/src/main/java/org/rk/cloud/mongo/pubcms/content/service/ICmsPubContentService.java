/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月18日 下午8:24:43
 */
package org.rk.cloud.mongo.pubcms.content.service;

import org.rk.cloud.mongo.pubcms.content.CmsPubContent;
import org.rk.core.nosql.mongodb.base.IBaseMongoService;

/**
 * @author cavion
 * @描述：
 */
public interface ICmsPubContentService extends IBaseMongoService<CmsPubContent> {

	CmsPubContent findByPubcmsId(String pubcmsId);

}
