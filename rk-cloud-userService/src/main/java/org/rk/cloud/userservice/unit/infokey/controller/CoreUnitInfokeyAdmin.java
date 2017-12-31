/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.unit.infokey.controller;

import java.util.List;

import org.rk.cloud.userservice.unit.infokey.CoreUnitInfokey;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月10日
 */
@Controller
@RequestMapping("/rk/admin/core/unit/unitInfoKey/")
public class CoreUnitInfokeyAdmin extends CoreUnitInfokeyWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUnitInfokey coreUnitInfokey) {
		if(RkObjectUtil.isEmpty(coreUnitInfokey.getId())){
			coreUnitInfokey.setCreator(SecurityUtil.getUserName());
			getCoreUnitInfokeyService().insertModel(coreUnitInfokey);
		}else{
			coreUnitInfokey.setUpdator(SecurityUtil.getUserName());
			getCoreUnitInfokeyService().updateModel(coreUnitInfokey);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUnitInfokeyService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
