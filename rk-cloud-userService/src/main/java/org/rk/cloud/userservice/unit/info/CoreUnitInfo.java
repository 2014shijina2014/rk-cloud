package org.rk.cloud.userservice.unit.info;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;

@TableMeta(name = "core_unit_info", catalog = "rk",desc="")
public class CoreUnitInfo extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="unitId",length=19,desc="单位id",defaultValue="")
	private Long unitId;
	@FieldMeta(name="keyCode",length=50,desc="扩展项代码",defaultValue="")
	private String keyCode;
	@FieldMeta(name="infoValue",length=300,desc="扩展项内容",defaultValue="")
	private String infoValue;

	public void setUnitId(Long unitId){
		this.unitId=unitId;
	}

	public Long getUnitId(){
		return unitId;
	}

	public void setKeyCode(String keyCode){
		this.keyCode=keyCode;
	}

	public String getKeyCode(){
		return keyCode;
	}

	public void setInfoValue(String infoValue){
		this.infoValue=infoValue;
	}

	public String getInfoValue(){
		return infoValue;
	}

}
