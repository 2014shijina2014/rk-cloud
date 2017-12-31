package org.rk.cloud.userservice.user.baseInfo;

import java.util.Date;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;
import org.springframework.format.annotation.DateTimeFormat;


@TableMeta(name = "core_user_base", catalog = "rk",desc="")
public class CoreUserBase extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="userId",length=19,desc="用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="name",length=20,desc="真实姓名",defaultValue="")
	private String name;
	@FieldMeta(name="gender",length=2,desc="性别",defaultValue="")
	private String gender;
	@FieldMeta(name="nation",length=2,desc="民族",defaultValue="")
	private String nation;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@FieldMeta(name="birthday",length=10,desc="出生日期",defaultValue="")
	private Date birthday;
	@FieldMeta(name="idCard",length=20,desc="身份证号",defaultValue="")
	private String idCard;

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setGender(String gender){
		this.gender=gender;
	}

	public String getGender(){
		return gender;
	}

	public void setNation(String nation){
		this.nation=nation;
	}

	public String getNation(){
		return nation;
	}

	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setIdCard(String idCard){
		this.idCard=idCard;
	}

	public String getIdCard(){
		return idCard;
	}

}
