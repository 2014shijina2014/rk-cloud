package org.rk.cloud.userservice.unit.infokey;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;

@TableMeta(name = "core_unit_infokey", catalog = "rk",desc="")
public class CoreUnitInfokey extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="keyCode",length=50,desc="扩展项代码",defaultValue="")
	private String keyCode;
	@FieldMeta(name="keyName",length=50,desc="扩展项名称",defaultValue="")
	private String keyName;
	@FieldMeta(name="dataLength",length=10,desc="扩展项数据长度",defaultValue="")
	private Integer dataLength;
	@FieldMeta(name="dataType",length=3,desc="扩展项数据类型",defaultValue="")
	private String dataType;
	@FieldMeta(name="selectType",length=3,desc="数据类型为选择项时选择类型",defaultValue="")
	private String selectType;
	@FieldMeta(name="selectCode",length=50,desc="选择项对应代码",defaultValue="")
	private String selectCode;
	@FieldMeta(name="showIndex",length=3,desc="显示优先索引",defaultValue="")
	private Integer showIndex;
	@FieldMeta(name="isMust",length=1,desc="是否必填",defaultValue="")
	private String isMust;
	@FieldMeta(name="enabled",length=1,desc="是否启用",defaultValue="")
	private String enabled;
	@FieldMeta(name="remark",length=200,desc="备注",defaultValue="")
	private String remark;

	public void setKeyCode(String keyCode){
		this.keyCode=keyCode;
	}

	public String getKeyCode(){
		return keyCode;
	}

	public void setKeyName(String keyName){
		this.keyName=keyName;
	}

	public String getKeyName(){
		return keyName;
	}

	public void setDataLength(Integer dataLength){
		this.dataLength=dataLength;
	}

	public Integer getDataLength(){
		return dataLength;
	}

	public void setDataType(String dataType){
		this.dataType=dataType;
	}

	public String getDataType(){
		return dataType;
	}

	public void setIsMust(String isMust){
		this.isMust=isMust;
	}

	public String getIsMust(){
		return isMust;
	}

	public void setEnabled(String enabled){
		this.enabled=enabled;
	}

	public String getEnabled(){
		return enabled;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getSelectCode() {
		return selectCode;
	}
	public void setSelectCode(String selectCode) {
		this.selectCode = selectCode;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

}
