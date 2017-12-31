package org.rk.cloud.userservice.user.baseInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.baseInfo.CoreUserBase;
import org.rk.cloud.userservice.user.baseInfo.service.ICoreUserBaseService;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rk/web/core/user/baseInfo/")
public class CoreUserBaseWeb extends BaseController<CoreUserBase> {
	@Resource(name = "CoreUserBaseService")
	private ICoreUserBaseService coreUserBaseService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = coreUserBaseService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<CoreUserBase> resultList = coreUserBaseService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUserBase coreUserBase = coreUserBaseService.selectModel(id);
		if (RkObjectUtil.isEmpty(coreUserBase)) {
			return ajaxError("没有找到数据");
		}
		return coreUserBase;
	}
	@RequestMapping(value = "queryCurrUser", method = RequestMethod.POST)
	public @ResponseBody Object queryCurrUser() {
		Long userId=SecurityUtil.getUserId();
		CoreUserBase coreUserBase = coreUserBaseService.selectModel("userId", userId);
		if (RkObjectUtil.isEmpty(coreUserBase)) {
			return null;
		}
		return coreUserBase;
	}
	//用户修改个人信息
	@RequestMapping(value = "saveCurr", method = RequestMethod.POST)
	public @ResponseBody Object saveCurr(CoreUserBase coreUserBase) {
		return save(coreUserBase);
	}
	
	public Object save(CoreUserBase coreUserBase) {
		if(RkObjectUtil.isEmpty(coreUserBase.getId())){
			Long userId=SecurityUtil.getUserId();
			CoreUserBase alCoreUserBase = coreUserBaseService.selectModel("userId", userId);
			if(!RkObjectUtil.isEmpty(alCoreUserBase)){
				coreUserBase.setId(alCoreUserBase.getId());
			}
		}
		if(RkObjectUtil.isEmpty(coreUserBase.getId())){
			coreUserBase.setCreator(SecurityUtil.getUserName());
			getCoreUserBaseService().insertModel(coreUserBase);
		}else{
			coreUserBase.setUpdator(SecurityUtil.getUserName());
			getCoreUserBaseService().updateModel(coreUserBase);
		}
		return ajaxSucc("保存成功");
	}
	
	
	public ICoreUserBaseService getCoreUserBaseService() {
		return coreUserBaseService;
	}

}
