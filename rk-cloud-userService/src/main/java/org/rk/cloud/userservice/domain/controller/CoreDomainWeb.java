/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
import org.rk.core.domain.domain.CoreDomain;
import org.rk.core.domain.service.ICoreDomainService;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion 2017年1月28日
 */
@Controller
@RequestMapping("/rk/web/core/domain/domain/")
public class CoreDomainWeb extends BaseController<CoreDomain> {
	@Resource(name = "CoreDomainService")
	private ICoreDomainService coreDomainService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String domainName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainName", "%" + domainName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreDomainService.selectListPage(params, page);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(String domainName,String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("domainName", "%" + domainName + "%");
		params.put("name", "%" + name + "%");
		params.put("owner", SecurityUtil.getUserName());
		List<CoreDomain> domainList = coreDomainService.selectModelList(params);
		return domainList;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreDomain coreDomain = coreDomainService.selectModel(id);
		return coreDomain;
	}
	

	public ICoreDomainService getCoreDomainService() {
		return coreDomainService;
	}

}
