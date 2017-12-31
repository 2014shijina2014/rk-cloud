package org.rk.cloud.userservice.user.domainUser;

import java.sql.Timestamp;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_domain_user", catalog = "rk",desc="")
public class CoreDomainUser extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="domainId",length=19,desc="站点id",defaultValue="")
	private Long domainId;
	@FieldMeta(name="userId",length=19,desc="用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="joinTime",length=19,desc="加入时间",defaultValue="")
	private Timestamp joinTime;
	@FieldMeta(name="isShield",length=1,desc="是否屏蔽，指用户是否屏蔽进入干站点",defaultValue="")
	private String isShield;
	@FieldMeta(name="isBlacklist",length=1,desc="是否黑名单，指是否吧该用户加入黑名单",defaultValue="")
	private String isBlacklist;
	@FieldMeta(name="enabled",length=1,desc="是否有效",defaultValue="")
	private String enabled;
	@FieldMeta(name="remark",length=200,desc="备注",defaultValue="")
	private String remark;

	public void setDomainId(Long domainId){
		this.domainId=domainId;
	}

	public Long getDomainId(){
		return domainId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setJoinTime(Timestamp joinTime){
		this.joinTime=joinTime;
	}

	public Timestamp getJoinTime(){
		return joinTime;
	}

	public void setIsShield(String isShield){
		this.isShield=isShield;
	}

	public String getIsShield(){
		return isShield;
	}

	public void setIsBlacklist(String isBlacklist){
		this.isBlacklist=isBlacklist;
	}

	public String getIsBlacklist(){
		return isBlacklist;
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
