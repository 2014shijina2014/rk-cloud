/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:34:41
 */
package org.rk.cloud.mongo.pubcms.content;

import java.io.Serializable;

import org.rk.core.nosql.mongodb.entity.MongoBaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author cavion
 * @描述：
 */
@Document(collection="cms_pub_content")
public class CmsPubContent extends MongoBaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2155018304086504050L;
	/**
	 * 关联数据库的内容id
	 */
	@Indexed
	private String pubcmsId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * seo关键词
	 */
	private String metaKeywords;
	/**
	 * seo描述信息
	 */
	private String metaDescription;
	
	
	public String getPubcmsId() {
		return pubcmsId;
	}
	public void setPubcmsId(String pubcmsId) {
		this.pubcmsId = pubcmsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	/**
	 * @return the metaDescription
	 */
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	
	
}
