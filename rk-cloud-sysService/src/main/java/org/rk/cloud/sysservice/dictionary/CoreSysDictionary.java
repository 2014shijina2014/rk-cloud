package org.rk.cloud.sysservice.dictionary;

import javax.validation.constraints.NotNull;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.TreeBean;
@TableMeta(name = "core_sys_dictionary", catalog = "rk",desc="")
public class CoreSysDictionary extends TreeBean{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@FieldMeta(name = "value", length = 300, desc = "字典值", defaultValue = "")
	private String value;
	@FieldMeta(name="enabled",length=1,desc="是否启用",defaultValue="1")
	private String enabled;
	@FieldMeta(name="isDic",length=1,desc="是否字典项",defaultValue="1")
	private String isDic;
	@FieldMeta(name="remark",length=200,desc="备注",defaultValue="null")
	private String remark;
	@FieldMeta(name = "bakField1", length = 100, desc = "备用字段1", defaultValue = "")
	private String bakField1;
	@FieldMeta(name = "bakField2", length = 100, desc = "备用字段2", defaultValue = "")
	private String bakField2;
	@FieldMeta(name = "bakField3", length = 100, desc = "备用字段3", defaultValue = "")
	private String bakField3;
	
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsDic() {
		return isDic;
	}

	public void setIsDic(String isDic) {
		this.isDic = isDic;
	}

	public String getBakField1() {
		return bakField1;
	}

	public void setBakField1(String bakField1) {
		this.bakField1 = bakField1;
	}

	public String getBakField2() {
		return bakField2;
	}

	public void setBakField2(String bakField2) {
		this.bakField2 = bakField2;
	}

	public String getBakField3() {
		return bakField3;
	}

	public void setBakField3(String bakField3) {
		this.bakField3 = bakField3;
	}

}
