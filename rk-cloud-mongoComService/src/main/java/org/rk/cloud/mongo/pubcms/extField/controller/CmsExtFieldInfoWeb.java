/**
 * @描述：
 * @author：caorendao187@163.com(cavion)
 * 2018年3月4日 下午12:05:35
 */
package org.rk.cloud.mongo.pubcms.extField.controller;

import java.util.List;

import javax.annotation.Resource;

import org.rk.cloud.mongo.pubcms.extField.CmsExtFieldInfo;
import org.rk.cloud.mongo.pubcms.extField.service.ICmsExtFieldInfoService;
import org.rk.core.common.util.RKDateUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
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
	@RequestMapping(value = "queryAll", method = RequestMethod.GET)
	public @ResponseBody Object queryAll(int start, int length, int draw, String fieldName, String localField, String resourceField) {
		List<CmsExtFieldInfo> allList = cmsExtFieldInfoService.findAll();
		return allList;
	}
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public @ResponseBody Object save(CmsExtFieldInfo cmsExtFieldInfo) {
		if(RkObjectUtil.isEmpty(cmsExtFieldInfo)) {
			cmsExtFieldInfo = new CmsExtFieldInfo();
		}
		cmsExtFieldInfo.setCreateTime(RKDateUtil.getCurrenDate().getTime());
		JSONObject fieldInfoToJson = new JSONObject();
		for(int i=0;i<10;i++) {
			fieldInfoToJson.put("testjsonfield"+i, Math.random()*100000);
		}
		cmsExtFieldInfo.setFieldInfoToJson(fieldInfoToJson);
		if(RkObjectUtil.isEmpty(cmsExtFieldInfo.getPubcmsId())){
			cmsExtFieldInfo.setPubcmsId("testpubcmsId");
		}
		getCmsExtFieldInfoService().insert(cmsExtFieldInfo);
		return ajaxSucc("保存成功");
	}
}
