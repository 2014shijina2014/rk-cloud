package  org.rk.cloud.field.user;

import org.rk.core.common.anno.FieldMeta;
import org.rk.core.common.anno.TableMeta;
import org.rk.core.common.bean.BaseBean;


@TableMeta(name = "user_extProp", catalog = "rk_field_manage",desc="")
public class UserExtProp extends BaseBean{

	private static final long serialVersionUID = 1L;
	@FieldMeta(name="moduleCode",length=50,desc="所属模块代码",defaultValue="")
	private String moduleCode;
	@FieldMeta(name="extOpraType",length=20,desc="扩展业务类型，如：用户信息、单位信息等",defaultValue="")
	private String extOpraType;
	@FieldMeta(name="extCode",length=100,desc="扩展字段名称",defaultValue="")
	private String extCode;
	@FieldMeta(name="extName",length=100,desc="扩展字段名称",defaultValue="")
	private String extName;
	@FieldMeta(name="extEnName",length=200,desc="扩展字段英文名称",defaultValue="")
	private String extEnName;
	@FieldMeta(name="dataLength",length=10,desc="数据长度",defaultValue="")
	private Integer dataLength;
	@FieldMeta(name="dataType",length=5,desc="数据类型",defaultValue="")
	private String dataType;
	@FieldMeta(name="selectDataType",length=5,desc="数据类型为选择时选择数据类型：1数据字典，2附加",defaultValue="")
	private String selectDataType;
	@FieldMeta(name="selectCode",length=50,desc="字典代码或附加数据项代码",defaultValue="")
	private String selectCode;
	@FieldMeta(name="selectType",length=2,desc="选择时选择类型：1单选，2多选",defaultValue="")
	private String selectType;
	@FieldMeta(name="showIndex",length=10,desc="显示优先顺序",defaultValue="")
	private Integer showIndex;
	@FieldMeta(name="isMust",length=1,desc="是否必须",defaultValue="")
	private String isMust;
	@FieldMeta(name="enabled",length=1,desc="是否启用",defaultValue="")
	private String enabled;
	@FieldMeta(name="remark",length=100,desc="备注",defaultValue="")
	private String remark;

	public void setModuleCode(String moduleCode){
		this.moduleCode=moduleCode;
	}

	public String getModuleCode(){
		return moduleCode;
	}

	public void setExtOpraType(String extOpraType){
		this.extOpraType=extOpraType;
	}

	public String getExtOpraType(){
		return extOpraType;
	}

	public void setExtCode(String extCode){
		this.extCode=extCode;
	}

	public String getExtCode(){
		return extCode;
	}

	public void setExtName(String extName){
		this.extName=extName;
	}

	public String getExtName(){
		return extName;
	}

	public void setExtEnName(String extEnName){
		this.extEnName=extEnName;
	}

	public String getExtEnName(){
		return extEnName;
	}

	public void setDataLength(Integer dataLength){
		this.dataLength=dataLength;
	}

	public Integer getDataLength(){
		return dataLength;
	}

	public void setDataType(String dataType){
		this.dataType=dataType;
	}

	public String getDataType(){
		return dataType;
	}

	public void setSelectDataType(String selectDataType){
		this.selectDataType=selectDataType;
	}

	public String getSelectDataType(){
		return selectDataType;
	}

	public void setSelectCode(String selectCode){
		this.selectCode=selectCode;
	}

	public String getSelectCode(){
		return selectCode;
	}

	public void setSelectType(String selectType){
		this.selectType=selectType;
	}

	public String getSelectType(){
		return selectType;
	}

	public void setShowIndex(Integer showIndex){
		this.showIndex=showIndex;
	}

	public Integer getShowIndex(){
		return showIndex;
	}

	public void setIsMust(String isMust){
		this.isMust=isMust;
	}

	public String getIsMust(){
		return isMust;
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
