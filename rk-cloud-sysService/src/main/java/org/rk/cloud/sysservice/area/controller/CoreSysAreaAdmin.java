/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.area.controller;

import java.util.List;

import org.rk.cloud.sysservice.area.CoreSysArea;
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
@RequestMapping("/rk/admin/core/sys/area/")
public class CoreSysAreaAdmin extends CoreSysAreaWeb{
	
	@RequestMapping(value="save",  method = RequestMethod.POST)
	 public @ResponseBody Object save(CoreSysArea coreSysArea){
		 if(RkObjectUtil.isEmpty(coreSysArea.getId())){
			 coreSysArea.setCreator(SecurityUtil.getUserName());
			 getCoreSysAreaService().insertTreeModel(coreSysArea);
		 }else{
			 coreSysArea.setUpdator(SecurityUtil.getUserName());
			 getCoreSysAreaService().updateModel(coreSysArea);
		 }
		 return ajaxSucc("保存成功");
	 }
	 @RequestMapping(value="batchDelete",  method = RequestMethod.POST)
	 public @ResponseBody Object batchDelete(@RequestParam("idArr[]") List<Long> idArr){
		 for (Long id : idArr) {
			 getCoreSysAreaService().deleteById(id);
		}
		return ajaxSucc("删除成功");
	 }
	 @RequestMapping(value="delete/{delId}",  method = RequestMethod.POST)
	 public @ResponseBody Object add(@PathVariable("delId") Long delId){
		 getCoreSysAreaService().deleteById(delId);
		return ajaxSucc("删除成功");
	 }
}
