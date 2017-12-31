package  org.rk.cloud.userservice.userThird.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rk/web/core/user/coreUserThirdWeb/")
public class CoreUserThirdAdmin extends CoreUserThirdWeb {
	@RequiresPermissions(value="coreUserThird:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreUserThird coreUserThird) {
		if(RkObjectUtil.isEmpty(coreUserThird.getId())){
			coreUserThird.setCreator(SecurityUtil.getUserName());
			getCoreUserThirdService().insertModel(coreUserThird);
		}else{
			coreUserThird.setUpdator(SecurityUtil.getUserName());
			getCoreUserThirdService().updateModel(coreUserThird);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="coreUserThird:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreUserThirdService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
