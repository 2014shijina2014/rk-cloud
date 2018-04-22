/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.userservice.domain.controller;

import java.util.List;

import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.domain.domain.CoreDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * 2017年1月28日
 */
@Controller
@RequestMapping("/rk/admin/core/domain/domain/")
public class CoreDomainAdmin extends CoreDomainWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CoreDomain coreDomain) {
		if (RkObjectUtil.isEmpty(coreDomain.getId())) {
			coreDomain.setCreator(SecurityUtil.getUserName());
			//coreDomain.setOwner(SecurityUtil.getUserName());
			//coreDomain.setOwnDate(RKDateUtil.getCurrentTimestamp());
			getCoreDomainService().insertModel(coreDomain);
		} else {
			coreDomain.setUpdator(SecurityUtil.getUserName());
			getCoreDomainService().updateModel(coreDomain);
		}
		return ajaxSucc("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestParam("idArr[]") List<Long> idArr) {
		for (Long id : idArr) {
			getCoreDomainService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	}
}
