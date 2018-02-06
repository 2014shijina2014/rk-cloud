/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年2月6日 下午4:34:41
 */
package org.rk.cloud.mongo.pubcms.extField;

import java.io.Serializable;

import org.rk.core.nosql.mongodb.entity.MongoBaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：
 */
@Document(collection="cms_ext_field_info")
public class CmsExtFieldInfo extends MongoBaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2155018304086504050L;
	/**
	 * 关联数据库的cmsid
	 */
	@Indexed
	private String pubcmsId;
	/**
	 * 扩展字段内容，json格式
	 */
	private JSONObject fieldInfoToJson;
	
	public String getPubcmsId() {
		return pubcmsId;
	}
	public void setPubcmsId(String pubcmsId) {
		this.pubcmsId = pubcmsId;
	}
	public JSONObject getFieldInfoToJson() {
		return fieldInfoToJson;
	}
	public void setFieldInfoToJson(JSONObject fieldInfoToJson) {
		this.fieldInfoToJson = fieldInfoToJson;
	}
}
