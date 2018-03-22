package org.rk.cloud.field.cms.programa;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.TreeBean;
@TableMeta(name = "cms_programa", catalog = "rk_field_manage",desc="")
public class CmsPrograma extends TreeBean{

	private static final long serialVersionUID = 1L;
	
	@FieldMeta(name="moduleCode",length=50,desc="所属模块编码",defaultValue="")
	private String moduleCode;
	@FieldMeta(name="description",length=200,desc="栏目描述",defaultValue="")
	private String description;
	@FieldMeta(name="slug",length=200,desc="slug",defaultValue="")
	private String slug;
	@FieldMeta(name="icon",length=200,desc="图标",defaultValue="")
	private String icon;
	@FieldMeta(name="orderNum",length=10,desc="排序号",defaultValue="")
	private int orderNum;
	@FieldMeta(name="metaKeywords",length=256,desc="SEO关键字",defaultValue="")
	private String metaKeywords;
	@FieldMeta(name="metaDescription",length=256,desc="SEO描述信息",defaultValue="")
	private String metaDescription;
	@FieldMeta(name="enabled",length=2,desc="是否启用",defaultValue="1")
	private String enabled;
	@FieldMeta(name="remark",length=200,desc="备注",defaultValue="")
	private String remark;

	public void setDescription(String description){
		this.description=description;
	}

	public String getDescription(){
		return description;
	}

	public void setSlug(String slug){
		this.slug=slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setModuleCode(String moduleCode){
		this.moduleCode=moduleCode;
	}

	public String getModuleCode(){
		return moduleCode;
	}

	public void setIcon(String icon){
		this.icon=icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setOrderNum(int orderNum){
		this.orderNum=orderNum;
	}

	public int getOrderNum(){
		return orderNum;
	}

	public void setMetaKeywords(String metaKeywords){
		this.metaKeywords=metaKeywords;
	}

	public String getMetaKeywords(){
		return metaKeywords;
	}

	public void setMetaDescription(String metaDescription){
		this.metaDescription=metaDescription;
	}

	public String getMetaDescription(){
		return metaDescription;
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
