/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.sysservice.dictionary.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
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
@RequestMapping("/rk/admin/core/sys/dictionary/")
public class CoreSysDictionaryAdmin extends CoreSysDictionaryWeb {
	@RequiresPermissions(value="coreSysDictionary:save")
	@RequestMapping(value="save",  method = RequestMethod.POST)
	 public @ResponseBody Object save(CoreSysDictionary coreSysDictionary){
		if(RkObjectUtil.isEmpty(coreSysDictionary.getId())){
			coreSysDictionary.setCreator(SecurityUtil.getUserName());
			getCoreSysDictionaryService().insertTreeModel(coreSysDictionary);
		}else{
			coreSysDictionary.setUpdator(SecurityUtil.getUserName());
			getCoreSysDictionaryService().updateModel(coreSysDictionary);
		}
		return ajaxSucc("保存成功");
	 }
	 @RequiresPermissions(value="coreSysDictionary:batchDelete")
	 @RequestMapping(value="batchDelete",  method = RequestMethod.POST)
	 public @ResponseBody Object batchDelete(@RequestParam("idArr[]") List<Long> idArr){
		 for (Long id : idArr) {
			 getCoreSysDictionaryService().deleteById(id);
		}
		 return ajaxSucc("删除成功");
	 }
	 
	 @RequiresPermissions(value="coreSysDictionary:delete")
	 @RequestMapping(value="delete/{delId}",  method = RequestMethod.POST)
	 public @ResponseBody Object delete(@PathVariable("delId") Long delId){
		getCoreSysDictionaryService().deleteById(delId);
		return ajaxSucc("删除成功");
	 }
}
