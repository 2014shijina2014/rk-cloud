/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.domainUser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.domainUser.CoreDomainUser;
import org.rk.cloud.userservice.user.domainUser.service.ICoreDomainUserService;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月10日
 */
@Controller
@RequestMapping("/rk/web/core/user/domainUser/")
public class CoreDomainUserWeb extends BaseController<CoreDomainUser> {
	@Resource(name = "CoreDomainUserService")
	private ICoreDomainUserService CoreDomainUserService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw,String userName,String domainName,String domain) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user.userName", "%" + userName + "%");
		params.put("domain.domainName", "%" + domain + "%");
		params.put("domain.name", "%" + domainName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = CoreDomainUserService.selectListPage(params, page);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(String userName,String domainName,String domain) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user.userName", "%" + userName + "%");
		params.put("domain.domainName", "%" + domain + "%");
		params.put("domain.name", "%" + domainName + "%");
		List<Map<String, Object>> result = CoreDomainUserService.selectList(params);
		return result;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreDomainUser userInfokey = CoreDomainUserService.selectModel(id);
		return userInfokey;
	}
	
	public ICoreDomainUserService getCoreDomainUserService() {
		return CoreDomainUserService;
	}

}
