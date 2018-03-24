/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月24日 下午9:46:31
 */
package org.rk.cloud.mongo.userExt.userExt;

import java.io.Serializable;

import org.rk.core.nosql.mongodb.entity.MongoBaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：用户扩展属性信息
 */
@Document(collection="user_ext_info")
public class UserExtInfo extends MongoBaseEntity implements Serializable{

	private static final long serialVersionUID = -1495468750478968082L;
	/**
	 * 关联数据库的会员号
	 */
	@Indexed
	private String menberNumber;
	/**
	 * 扩展字段内容，json格式
	 */
	private JSONObject userExtInfoJson;
	
	public String getMenberNumber() {
		return menberNumber;
	}
	public void setMenberNumber(String menberNumber) {
		this.menberNumber = menberNumber;
	}
	public JSONObject getUserExtInfoJson() {
		return userExtInfoJson;
	}
	public void setUserExtInfoJson(JSONObject userExtInfoJson) {
		this.userExtInfoJson = userExtInfoJson;
	}
	
}
