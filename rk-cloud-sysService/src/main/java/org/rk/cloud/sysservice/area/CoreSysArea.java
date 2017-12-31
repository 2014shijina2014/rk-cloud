package org.rk.cloud.sysservice.area;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.TreeBean;

@TableMeta(name = "core_sys_area", catalog = "rk", desc = "")
public class CoreSysArea extends TreeBean {

	private static final long serialVersionUID = 1L;
	@FieldMeta(name = "areaNum", length = 10, desc = "区域代码", defaultValue = "")
	private String areaNum;
	@FieldMeta(name = "areaType", length = 2, desc = "行政类型", defaultValue = "")
	private String areaType;
	@FieldMeta(name = "areaShortName", length = 10, desc = "简称", defaultValue = "")
	private String areaShortName;
	@FieldMeta(name = "enabled", length = 1, desc = "是否有效", defaultValue = "")
	private String enabled;
	@FieldMeta(name = "remark", length = 100, desc = "备注", defaultValue = "")
	private String remark;
	@FieldMeta(name = "localPerson", length = 20, desc = "常驻人口（万人）", defaultValue = "")
	private String localPerson;
	@FieldMeta(name = "personDensity", length = 20, desc = "人口密度（人/千平米）", defaultValue = "")
	private String personDensity;
	@FieldMeta(name = "areaSize", length = 20, desc = "占地面积（千平米）", defaultValue = "")
	private String areaSize;
	@FieldMeta(name = "bakField1", length = 100, desc = "备用字段1", defaultValue = "")
	private String bakField1;
	@FieldMeta(name = "bakField2", length = 100, desc = "备用字段2", defaultValue = "")
	private String bakField2;
	@FieldMeta(name = "bakField3", length = 100, desc = "备用字段3", defaultValue = "")
	private String bakField3;


	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	public String getAreaNum() {
		return areaNum;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaShortName() {
		return areaShortName;
	}

	public void setAreaShortName(String areaShortName) {
		this.areaShortName = areaShortName;
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

	public String getLocalPerson() {
		return localPerson;
	}

	public void setLocalPerson(String localPerson) {
		this.localPerson = localPerson;
	}

	public String getPersonDensity() {
		return personDensity;
	}

	public void setPersonDensity(String personDensity) {
		this.personDensity = personDensity;
	}

	public String getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(String areaSize) {
		this.areaSize = areaSize;
	}

}
