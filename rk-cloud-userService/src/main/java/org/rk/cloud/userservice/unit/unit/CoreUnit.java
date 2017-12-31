package org.rk.cloud.userservice.unit.unit;

import java.sql.Timestamp;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_unit", catalog = "rk",desc="")
public class CoreUnit extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="unitName",length=200,desc="单位名称",defaultValue="")
	private String unitName;
	@FieldMeta(name="address",length=300,desc="单位地址",defaultValue="")
	private String address;
	@FieldMeta(name="email",length=50,desc="单位固定联系邮箱",defaultValue="")
	private String email;
	@FieldMeta(name="telphone",length=20,desc="联系电话",defaultValue="")
	private String telphone;
	@FieldMeta(name="unitType",length=4,desc="单位类型",defaultValue="")
	private String unitType;
	@FieldMeta(name="unitIntro",length=300,desc="单位简介",defaultValue="")
	private String unitIntro;
	@FieldMeta(name="unitDetail",length=65535,desc="单位详细介绍",defaultValue="")
	private String unitDetail;
	@FieldMeta(name="isActive",length=1,desc="是否激活",defaultValue="")
	private String isActive;
	@FieldMeta(name="activeDate",length=19,desc="激活时间",defaultValue="")
	private Timestamp activeDate;
	@FieldMeta(name="enable",length=1,desc="是否启用",defaultValue="")
	private String enable;
	@FieldMeta(name="remark",length=200,desc="备注",defaultValue="")
	private String remark;

	public void setUnitName(String unitName){
		this.unitName=unitName;
	}

	public String getUnitName(){
		return unitName;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return email;
	}

	public void setTelphone(String telphone){
		this.telphone=telphone;
	}

	public String getTelphone(){
		return telphone;
	}

	public void setUnitType(String unitType){
		this.unitType=unitType;
	}

	public String getUnitType(){
		return unitType;
	}

	public void setUnitIntro(String unitIntro){
		this.unitIntro=unitIntro;
	}

	public String getUnitIntro(){
		return unitIntro;
	}

	public void setUnitDetail(String unitDetail){
		this.unitDetail=unitDetail;
	}

	public String getUnitDetail(){
		return unitDetail;
	}

	public void setIsActive(String isActive){
		this.isActive=isActive;
	}

	public String getIsActive(){
		return isActive;
	}

	public void setActiveDate(Timestamp activeDate){
		this.activeDate=activeDate;
	}

	public Timestamp getActiveDate(){
		return activeDate;
	}

	public void setEnable(String enable){
		this.enable=enable;
	}

	public String getEnable(){
		return enable;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

}
