package org.rk.cloud.userservice.unit.unitAdmin;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_unit_admin", catalog = "rk",desc="")
public class CoreUnitAdmin extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="unitId",length=19,desc="单位id",defaultValue="")
	private Long unitId;
	@FieldMeta(name="userId",length=19,desc="用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="isOwner",length=1,desc="是否单用拥有者",defaultValue="")
	private String isOwner;
	@FieldMeta(name="enabled",length=1,desc="是否启用",defaultValue="")
	private String enabled;

	public void setUnitId(Long unitId){
		this.unitId=unitId;
	}

	public Long getUnitId(){
		return unitId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setIsOwner(String isOwner){
		this.isOwner=isOwner;
	}

	public String getIsOwner(){
		return isOwner;
	}

	public void setEnabled(String enabled){
		this.enabled=enabled;
	}

	public String getEnabled(){
		return enabled;
	}

}
