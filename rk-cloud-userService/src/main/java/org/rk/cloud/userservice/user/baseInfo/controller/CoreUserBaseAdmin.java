package org.rk.cloud.userservice.user.baseInfo.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rk/admin/core/user/baseInfo/")
public class CoreUserBaseAdmin extends CoreUserBaseWeb {
	@RequiresPermissions(value="coreUserBase:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserBase coreUserBase) {
		return super.save(coreUserBase);
	}

	@RequiresPermissions(value="coreUserBase:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserBaseService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
