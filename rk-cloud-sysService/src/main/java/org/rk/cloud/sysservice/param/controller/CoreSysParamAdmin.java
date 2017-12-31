/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.param.controller;

import java.util.List;

import org.rk.cloud.sysservice.param.CoreSysParam;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion
 * 2017年1月27日
 */
@Controller
@RequestMapping("/rk/admin/core/sys/param/")
public class CoreSysParamAdmin extends CoreSysParamWeb{
	
	@RequestMapping(value="save",  method = RequestMethod.POST)
	 public @ResponseBody Object save(CoreSysParam coreSysParam){
		 if(RkObjectUtil.isEmpty(coreSysParam.getId())){
			 coreSysParam.setCreator(SecurityUtil.getUserName());
			 getCoreSysParamService().insertModel(coreSysParam);
		 }else{
			 coreSysParam.setUpdator(SecurityUtil.getUserName());
			 getCoreSysParamService().updateModel(coreSysParam);
		 }
		 return ajaxSucc("保存成功");
	 }
	 @RequestMapping(value="batchDelete",  method = RequestMethod.POST)
	 public @ResponseBody Object batchDelete(@RequestParam("idArr[]") List<Long> idArr){
		 for (Long id : idArr) {
			 getCoreSysParamService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	 }
	 @RequestMapping(value="delete/{delId}",  method = RequestMethod.POST)
	 public @ResponseBody Object add(@PathVariable("delId") Long delId){
		 getCoreSysParamService().deleteById(delId);
		return ajaxSucc("删除成功");
	 }
}
