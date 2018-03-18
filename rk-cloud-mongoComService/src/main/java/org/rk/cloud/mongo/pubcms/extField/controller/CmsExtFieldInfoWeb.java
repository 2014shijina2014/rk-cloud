/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:05:35
 */
package org.rk.cloud.mongo.pubcms.extField.controller;

import javax.annotation.Resource;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.cloud.mongo.pubcms.extField.service.ICmsExtFieldInfoService;
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
@RequestMapping("/rk/web/mongo/pubcms/extField/")
public class CmsExtFieldInfoWeb extends BaseController<CmsExtFieldInfo>{
	@Resource(name = "cmsExtFieldInfoService")
	private ICmsExtFieldInfoService cmsExtFieldInfoService;
	
	public ICmsExtFieldInfoService getCmsExtFieldInfoService() {
		return cmsExtFieldInfoService;
	}
	public void setCmsExtFieldInfoService(ICmsExtFieldInfoService cmsExtFieldInfoService) {
		this.cmsExtFieldInfoService = cmsExtFieldInfoService;
	}
	@RequestMapping(value = "queryByPubcmsId", method = RequestMethod.POST)
	public @ResponseBody Object queryByPubcmsId(String pubcmsId) {
		return getCmsExtFieldInfoService().findByPubcmsId(pubcmsId);
	}
}
