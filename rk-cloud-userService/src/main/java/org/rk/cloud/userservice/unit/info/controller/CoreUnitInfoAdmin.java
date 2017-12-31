/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.unit.info.controller;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.cloud.userservice.unit.unit.CoreUnit;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月11日
 */
@Controller
@RequestMapping("/rk/admin/core/unit/unitInfo/")
public class CoreUnitInfoAdmin extends CoreUnitInfoWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(@RequestParam()Map<String,Object> params) {
		CoreUnit coreUnit=new CoreUnit();
		Long unitId=null;
		if(!RkObjectUtil.isEmpty(params.get("id"))){
			unitId=Long.parseLong((String)params.get("id"));
		}
		coreUnit.setId(unitId);
		coreUnit.setUnitName((String)params.get("unitName"));
		coreUnit.setEmail((String)params.get("email"));
		coreUnit.setAddress((String)params.get("address"));
		coreUnit.setTelphone((String)params.get("telphone"));
		coreUnit.setUnitDetail((String)params.get("unitDetail"));
		coreUnit.setUnitIntro((String)params.get("unitIntro"));
		coreUnit.setUnitType((String)params.get("unitType"));
		if(RkObjectUtil.isEmpty(coreUnit.getId())){
			coreUnit.setCreator(SecurityUtil.getUserName());
			coreUnit.setIsActive(RkConst.yesno.no);//默认未激活
			unitId=getCoreUnitService().insertModel(coreUnit);
		}else{
			coreUnit.setUpdator(SecurityUtil.getUserName());
			getCoreUnitService().updateModel(coreUnit);
			unitId=coreUnit.getId();
		}
		//保存扩展信息
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key =entry.getKey();
			String value=(String) entry.getValue();
			if(key.equals("id")||key.equals("unitName")||key.equals("email")||key.equals("address")||key.equals("telphone")||key.equals("unitDetail")
					||key.equals("unitIntro")||key.equals("unitType")){
				continue;
			}
			CoreUnitInfo coreUnitInfo=new CoreUnitInfo();
			coreUnitInfo.setUnitId(unitId);
			coreUnitInfo.setKeyCode(key);
			coreUnitInfo.setInfoValue(value);
			coreUnitInfo.setCreator(SecurityUtil.getUserName());
			getCoreUnitInfoService().updateUnitInfo(coreUnitInfo);
		}
		return ajaxSucc("保存成功");
	}
	@RequestMapping(value = "saveExtInfo", method = RequestMethod.POST)
	public @ResponseBody Object saveExtInfo(@RequestParam()Map<String,Object> params) {
		Long unitId=null;
		if(!RkObjectUtil.isEmpty(params.get("id"))){
			unitId=Long.parseLong((String)params.get("id"));
		}else{
			RKAlert.Error("请指定单位id");
		}
		//保存扩展信息
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key =entry.getKey();
			String value=(String) entry.getValue();
			if(key.equals("id")||key.equals("unitName")||key.equals("email")||key.equals("address")||key.equals("telphone")||key.equals("unitDetail")
					||key.equals("unitIntro")||key.equals("unitType")){
				continue;
			}
			CoreUnitInfo coreUnitInfo=new CoreUnitInfo();
			coreUnitInfo.setUnitId(unitId);
			coreUnitInfo.setKeyCode(key);
			coreUnitInfo.setInfoValue(value);
			coreUnitInfo.setCreator(SecurityUtil.getUserName());
			getCoreUnitInfoService().updateUnitInfo(coreUnitInfo);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUnitInfoService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
