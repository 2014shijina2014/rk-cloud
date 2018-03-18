/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:05:35
 */
package org.rk.cloud.mongo.pubcms.content.controller;

import javax.annotation.Resource;

import org.rk.cloud.mongo.pubcms.content.CmsPubContent;
import org.rk.cloud.mongo.pubcms.content.service.ICmsPubContentService;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * @描述：
 */
@Controller
@RequestMapping("/rk/web/mongo/pubcms/pubContent/")
public class CmsPubContentWeb extends BaseController<CmsPubContent>{
	@Resource(name = "cmsPubContentService")
	private ICmsPubContentService cmsPubContentService;
	
	public ICmsPubContentService getCmsPubContentService() {
		return cmsPubContentService;
	}
	public void setCmsPubContentService(ICmsPubContentService cmsPubContentService) {
		this.cmsPubContentService = cmsPubContentService;
	}
	
	@RequestMapping(value = "queryByPubcmsId", method = RequestMethod.POST)
	public @ResponseBody Object queryByPubcmsId(String pubcmsId) {
		return getCmsPubContentService().findByPubcmsId(pubcmsId);
	}
}
