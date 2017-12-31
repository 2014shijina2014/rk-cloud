/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.userAddress.controller;

import java.util.List;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
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
@RequestMapping("/rk/admin/core/user/userAddress/")
public class CoreUserAddressAdmin extends CoreUserAddressWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserAddress coreUserAddress) {
		if(RkObjectUtil.isEmpty(coreUserAddress.getId())){
			coreUserAddress.setCreator(SecurityUtil.getUserName());
			getCoreUserAddressService().insertModel(coreUserAddress);
		}else{
			coreUserAddress.setUpdator(SecurityUtil.getUserName());
			getCoreUserAddressService().updateModel(coreUserAddress);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserAddressService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
