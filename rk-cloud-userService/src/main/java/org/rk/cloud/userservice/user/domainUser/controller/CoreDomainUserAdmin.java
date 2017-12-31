/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.domainUser.controller;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.user.domainUser.CoreDomainUser;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
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
@RequestMapping("/rk/admin/core/user/domainUser/")
public class CoreDomainUserAdmin extends CoreDomainUserWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreDomainUser coreDomainUser) {
		if(RkObjectUtil.isEmpty(coreDomainUser.getId())){
			coreDomainUser.setCreator(SecurityUtil.getUserName());
			getCoreDomainUserService().insertModel(coreDomainUser);
		}else{
			coreDomainUser.setUpdator(SecurityUtil.getUserName());
			getCoreDomainUserService().updateModel(coreDomainUser);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreDomainUserService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
	
	@RequestMapping(value = "queryAdminDomain", method = RequestMethod.POST)
	public @ResponseBody Object queryAdminDomain() {
		Long userId=SecurityUtil.getUserId();
		List<Map<String, Object>> result = getCoreDomainUserService().selectListByUserId(userId);
		if(RkObjectUtil.isEmpty(result)||result.size()<1){
			return ajaxError("你没有任何站点管理权");
		}else{
			for (Map<String, Object> map : result) {
				String userType=(String) map.get("userType");
				if(Integer.parseInt(userType)>20){
					result.remove(map);
				}
			}
		}
		if(RkObjectUtil.isEmpty(result)||result.size()<1){
			return ajaxError("你没有任何站点管理权");
		}
		return result;
	}
	@RequestMapping(value = "queryDomainUser", method = RequestMethod.POST)
	public @ResponseBody Object queryDomainUser(int start, int length, int draw,Long domainId) {
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		if(RkObjectUtil.isEmpty(domainId)){
			return ajaxError("请选择指定站点");
		}
		page=getCoreDomainUserService().selectListPageByDomainId(domainId, page);
		return page;
	}
}
