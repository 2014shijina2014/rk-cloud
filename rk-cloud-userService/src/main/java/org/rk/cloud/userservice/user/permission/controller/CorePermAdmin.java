/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.permission.controller;

import java.util.List;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/user/perm/")
public class CorePermAdmin extends CorePermWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CorePerm corePerm) {
		if(RkObjectUtil.isEmpty(corePerm.getId())){
			corePerm.setCreator(SecurityUtil.getUserName());
			getCorePermService().insertModel(corePerm);
		}else{
			corePerm.setUpdator(SecurityUtil.getUserName());
			getCorePermService().updateModel(corePerm);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCorePermService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
