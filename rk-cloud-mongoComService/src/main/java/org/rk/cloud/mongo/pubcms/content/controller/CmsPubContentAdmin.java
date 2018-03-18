/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:07:55
 */
package org.rk.cloud.mongo.pubcms.content.controller;

import org.rk.cloud.mongo.pubcms.content.CmsPubContent;
import org.rk.core.common.util.RKDateUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * @描述：
 */
@Controller
@RequestMapping("/rk/admin/mongo/pubcms/pubContent/")
public class CmsPubContentAdmin extends CmsPubContentWeb {
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CmsPubContent cmsPubContent) {
		if(RkObjectUtil.isEmpty(cmsPubContent)) {
			cmsPubContent = new CmsPubContent();
		}
		cmsPubContent.setCreateTime(RKDateUtil.getCurrenDate().getTime());
		getCmsPubContentService().insert(cmsPubContent);
		return ajaxSucc("保存成功");
	}
}
