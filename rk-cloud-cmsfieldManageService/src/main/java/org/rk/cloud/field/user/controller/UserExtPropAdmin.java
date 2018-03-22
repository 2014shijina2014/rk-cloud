package  org.rk.cloud.field.user.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import  org.rk.cloud.field.user.UserExtProp;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/rk/admin/field/cms/userExtProp/")
public class UserExtPropAdmin extends UserExtPropWeb {
	@RequiresPermissions(value="userExtProp:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(UserExtProp userExtProp) {
		if(RkObjectUtil.isEmpty(userExtProp.getId())){
			userExtProp.setCreator(SecurityUtil.getUserName());
			getUserExtPropService().insertModel(userExtProp);
		}else{
			userExtProp.setUpdator(SecurityUtil.getUserName());
			getUserExtPropService().updateModel(userExtProp);
		}
		return ajaxSucc("保存成功");
	}

	@RequiresPermissions(value="userExtProp:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getUserExtPropService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
