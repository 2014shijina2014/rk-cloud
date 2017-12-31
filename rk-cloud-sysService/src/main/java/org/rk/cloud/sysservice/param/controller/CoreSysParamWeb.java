/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.param.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.cloud.sysservice.param.service.ICoreSysParamService;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * 2017年1月27日
 */
@Controller
@RequestMapping("/rk/web/core/sys/param/")
public class CoreSysParamWeb extends BaseController<CoreSysParam> {
	@Resource(name = "CoreSysParamService")
	 private ICoreSysParamService coreSysParamService;
	 
	 @RequestMapping(value="queryPage",  method = RequestMethod.POST)
	 public @ResponseBody Object queryPage(int start,int length,int draw,String code,String name){
		 Map<String, Object> params=new HashMap<String, Object>();
		 params.put("code", "%"+code+"%");
		 params.put("name", "%"+name+"%");
		 PageData page=new PageData();
		 page.setStart(start);
		 page.setLength(length);
		 page.setDraw(draw);
		 page=coreSysParamService.selectModelListPage(params, page);
		 return page;
	 }
	 @RequestMapping(value="queryList",  method = RequestMethod.GET)
	 public @ResponseBody Object queryList(String code, String name, String paramType) {
		 Map<String, Object> params=new HashMap<String, Object>();
		 params.put("code", "%"+code+"%");
		 params.put("name", "%"+name+"%");
		 params.put("paramType", paramType);
		 return coreSysParamService.selectModelList(params);
	 }
	 
	public ICoreSysParamService getCoreSysParamService() {
		return coreSysParamService;
	}
	 
	 
}
