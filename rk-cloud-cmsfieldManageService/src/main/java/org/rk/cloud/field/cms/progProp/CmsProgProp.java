package org.rk.cloud.field.cms.progProp;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "cms_prog_prop", catalog = "rk_field_manage",desc="")
public class CmsProgProp extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="domainCode",length=100,desc="所属站点code",defaultValue="")
	private String domainCode;
	@FieldMeta(name="programaCode",length=100,desc="产品分类code",defaultValue="")
	private String programaCode;
	@FieldMeta(name="propertyCode",length=100,desc="属性代码code",defaultValue="")
	private String propertyCode;
	@FieldMeta(name="orderNum",length=5,desc="排序号",defaultValue="")
	private Integer orderNum;
	@FieldMeta(name="isMust",length=1,desc="是否必须",defaultValue="0")
	private String isMust;
	@FieldMeta(name="enabled",length=1,desc="是否启用",defaultValue="1")
	private String enabled;
	@FieldMeta(name="remark",length=100,desc="备注",defaultValue="")
	private String remark;

	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	public void setProgramaCode(String programaCode){
		this.programaCode=programaCode;
	}

	public String getProgramaCode(){
		return programaCode;
	}

	public void setPropertyCode(String propertyCode){
		this.propertyCode=propertyCode;
	}

	public String getPropertyCode(){
		return propertyCode;
	}

	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
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

}
