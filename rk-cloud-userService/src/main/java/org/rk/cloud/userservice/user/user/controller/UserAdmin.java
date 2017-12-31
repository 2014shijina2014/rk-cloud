/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.tools.office.util.excelUtil.ExcelUtil;
import org.rk.core.user.user.CoreUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author cavion（曹仁道）
 * @date 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/user/user/")
public class UserAdmin extends UserWeb{
	
	@RequestMapping(value = "adminRegister", method = RequestMethod.POST)
	public @ResponseBody Object adminRegister(CoreUser coreUser) {
		coreUser.setUserType(RkConst.userType.domainAdmin);//这是站点管理员用户
		return createUser(coreUser);
	}
	@RequestMapping(value = "exportUserInfo", method = RequestMethod.GET)
	public @ResponseBody Object exportUserInfo(HttpServletRequest request, HttpServletResponse response) {
		List<CoreUser> userList=getCoreUserService().selectModelList(null);
		List<Map<String,Object>> contentList=new ArrayList<Map<String,Object>>();
		for (CoreUser coreUser : userList) {
			Map<String,Object> content=RKBeanUtil.convertBeanToMap(coreUser);
			contentList.add(content);
		}
		ExcelUtil.exportExcel(request, response, 1,contentList,"userInfoExport");
		return null;
	}
}
