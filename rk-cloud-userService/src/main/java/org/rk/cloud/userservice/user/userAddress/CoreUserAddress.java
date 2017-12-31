package org.rk.cloud.userservice.user.userAddress;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "core_user_address", catalog = "rk",desc="")
public class CoreUserAddress extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="userId",length=19,desc="用户id",defaultValue="")
	private Long userId;
	@FieldMeta(name="area",length=100,desc="区域",defaultValue="")
	private String area;
	@FieldMeta(name="address",length=300,desc="详细地址",defaultValue="")
	private String address;
	@FieldMeta(name="recivor",length=50,desc="收货人",defaultValue="")
	private String recivor;
	@FieldMeta(name="cellphone",length=30,desc="手机号",defaultValue="")
	private String cellphone;
	@FieldMeta(name="phone",length=30,desc="电话号码",defaultValue="")
	private String phone;
	@FieldMeta(name="postcode",length=10,desc="邮编",defaultValue="")
	private String postcode;
	@FieldMeta(name="isDefault",length=1,desc="是否默认",defaultValue="")
	private String isDefault;
	@FieldMeta(name="enabled",length=1,desc="是否有效",defaultValue="")
	private String enabled;
	@FieldMeta(name="remark",length=100,desc="备注",defaultValue="")
	private String remark;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setArea(String area){
		this.area=area;
	}

	public String getArea(){
		return area;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setRecivor(String recivor){
		this.recivor=recivor;
	}

	public String getRecivor(){
		return recivor;
	}

	public void setCellphone(String cellphone){
		this.cellphone=cellphone;
	}

	public String getCellphone(){
		return cellphone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPostcode(String postcode){
		this.postcode=postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setIsDefault(String isDefault){
		this.isDefault=isDefault;
	}

	public String getIsDefault(){
		return isDefault;
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
