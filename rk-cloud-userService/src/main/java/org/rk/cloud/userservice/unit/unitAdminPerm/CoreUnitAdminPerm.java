package org.rk.cloud.userservice.unit.unitAdminPerm;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_unit_admin_perm", catalog = "rk",desc="")
public class CoreUnitAdminPerm extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="unitAdminId",length=19,desc="单位管理员id",defaultValue="")
	private Long unitAdminId;
	@FieldMeta(name="permCode",length=100,desc="拥有的权限代码",defaultValue="")
	private String permCode;
	@FieldMeta(name="enabled",length=1,desc="是否有效",defaultValue="")
	private String enabled;

	public void setUnitAdminId(Long unitAdminId){
		this.unitAdminId=unitAdminId;
	}

	public Long getUnitAdminId(){
		return unitAdminId;
	}

	public void setPermCode(String permCode){
		this.permCode=permCode;
	}

	public String getPermCode(){
		return permCode;
	}

	public void setEnabled(String enabled){
		this.enabled=enabled;
	}

	public String getEnabled(){
		return enabled;
	}

}
