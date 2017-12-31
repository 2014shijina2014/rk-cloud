package  org.rk.cloud.userservice.userThird;

import java.sql.Timestamp;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_user_third", catalog = "rk",desc="")
public class CoreUserThird extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="userId",length=19,desc="关联本地用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="thirdCode",length=50,desc="第三方类型代码",defaultValue="")
	private String thirdCode;
	@FieldMeta(name="openId",length=128,desc="openId",defaultValue="")
	private String openId;
	@FieldMeta(name="sessionKey",length=128,desc="session_key",defaultValue="")
	private String sessionKey;
	@FieldMeta(name="lastLoginTime",length=19,desc="最后一次登陆时间",defaultValue="")
	private Timestamp lastLoginTime;

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setThirdCode(String thirdCode){
		this.thirdCode=thirdCode;
	}

	public String getThirdCode(){
		return thirdCode;
	}

	public void setOpenId(String openId){
		this.openId=openId;
	}

	public String getOpenId(){
		return openId;
	}

	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}

	public String getSessionKey(){
		return sessionKey;
	}

	public void setLastLoginTime(Timestamp lastLoginTime){
		this.lastLoginTime=lastLoginTime;
	}

	public Timestamp getLastLoginTime(){
		return lastLoginTime;
	}

}
