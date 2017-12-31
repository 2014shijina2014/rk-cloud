package org.rk.cloud.userservice.user.info;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_user_info", catalog = "rk",desc="")
public class CoreUserInfo extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="userId",length=19,desc="用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="keyCode",length=50,desc="扩展项代码",defaultValue="")
	private String keyCode;
	@FieldMeta(name="infoValue",length=300,desc="扩展项值",defaultValue="")
	private String infoValue;

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return userId;
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
