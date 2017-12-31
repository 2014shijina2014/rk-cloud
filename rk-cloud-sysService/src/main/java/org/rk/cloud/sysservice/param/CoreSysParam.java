package org.rk.cloud.sysservice.param;

import javax.validation.constraints.NotNull;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;

@TableMeta(name = "core_sys_param", catalog = "rk", desc = "")
public class CoreSysParam extends BaseBean {

	private static final long serialVersionUID = 1L;
	@NotNull
	@FieldMeta(name = "code", length = 100, desc = "系统参数代码", defaultValue = "")
	private String code;
	@NotNull
	@FieldMeta(name = "value", length = 300, desc = "系统参数值", defaultValue = "")
	private String value;
	@FieldMeta(name = "name", length = 300, desc = "系统参数名称", defaultValue = "")
	private String name;
	@FieldMeta(name = "paramType", length = 20, desc = "参数类型", defaultValue = "")
	private String paramType;
	@FieldMeta(name = "enabled", length = 2, desc = "是否启用", defaultValue = "1")
	private String enabled;
	@FieldMeta(name = "remark", length = 200, desc = "备注", defaultValue = "")
	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

}
