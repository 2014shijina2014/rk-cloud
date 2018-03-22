package org.rk.cloud.field.cms.property;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "cms_property", catalog = "rk_field_manage",desc="")
public class CmsProperty extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="moduleCode",length=100,desc="所属模块代码",defaultValue="")
	private String moduleCode;
	@FieldMeta(name="code",length=100,desc="属性代码",defaultValue="")
	private String code;
	@FieldMeta(name="name",length=100,desc="属性名称",defaultValue="")
	private String name;
	@FieldMeta(name="enName",length=200,desc="属性英文名称",defaultValue="")
	private String enName;
	@FieldMeta(name="dataLength",length=10,desc="数据长度",defaultValue="")
	private Integer dataLength;
	@FieldMeta(name="dataType",length=5,desc="数据类型",defaultValue="")
	private String dataType;
	@FieldMeta(name="selectDataType",length=5,desc="数据类型为选择时选择数据类型：1数据字典，2附加",defaultValue="")
	private String selectDataType;
	@FieldMeta(name="selectCode",length=50,desc="字典代码或附加数据项代码",defaultValue="")
	private String selectCode;
	@FieldMeta(name="selectType",length=2,desc="选择时选择类型：1单选，2多选",defaultValue="")
	private String selectType;
	@FieldMeta(name="remark",length=200,desc="备注说明",defaultValue="")
	private String remark;
	@FieldMeta(name="enabled",length=1,desc="是否有效",defaultValue="")
	private String enabled;

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setEnName(String enName){
		this.enName=enName;
	}

	public String getEnName(){
		return enName;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSelectDataType() {
		return selectDataType;
	}

	public void setSelectDataType(String selectDataType) {
		this.selectDataType = selectDataType;
	}

	public String getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(String selectCode) {
		this.selectCode = selectCode;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setEnabled(String enabled){
		this.enabled=enabled;
	}

	public String getEnabled(){
		return enabled;
	}

}
