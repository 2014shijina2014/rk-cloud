/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:07:55
 */
package org.rk.cloud.mongo.pubcms.extField.controller;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.core.common.util.RKDateUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cavion
 * @描述：
 */
@Controller
@RequestMapping("/rk/admin/mongo/pubcms/extField/")
public class CmsExtFieldInfoAdmin extends CmsExtFieldInfoWeb {
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Object save(CmsExtFieldInfo cmsExtFieldInfo,JSONObject fieldInfoToJson) {
		if(RkObjectUtil.isEmpty(cmsExtFieldInfo)) {
			cmsExtFieldInfo = new CmsExtFieldInfo();
		}
		cmsExtFieldInfo.setCreateTime(RKDateUtil.getCurrenDate().getTime());
		cmsExtFieldInfo.setFieldInfoToJson(fieldInfoToJson);
		if(RkObjectUtil.isEmpty(cmsExtFieldInfo.getPubcmsId())){
			ajaxError("pubcmsId不可为空");
		}
		getCmsExtFieldInfoService().insert(cmsExtFieldInfo);
		return ajaxSucc("保存成功");
	}
}
