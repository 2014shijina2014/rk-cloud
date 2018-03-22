package  org.rk.cloud.field.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import  org.rk.cloud.field.user.UserExtProp;
import  org.rk.cloud.field.user.service.IUserExtPropService;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rk/web/field/cms/userExtProp/")
public class UserExtPropWeb extends BaseController<UserExtProp> {
	@Resource(name = "UserExtPropService")
	private IUserExtPropService userExtPropService;
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public Object queryPage(int start, int length, int draw) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = userExtPropService.selectModelListPage(params, page);
		return page;
		}

	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public Object queryList() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserExtProp> resultList = userExtPropService.selectModelList(params);
		if (RkObjectUtil.isEmpty(resultList)||resultList.size()<1) {
			return ajaxError("没有找到数据");
		}
		return resultList;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public Object query(Long id) {
		UserExtProp userExtProp = userExtPropService.selectModel(id);
		if (RkObjectUtil.isEmpty(userExtProp)) {
			return ajaxError("没有找到数据");
		}
		return userExtProp;
	}
	public IUserExtPropService getUserExtPropService() {
		return userExtPropService;
	}

}
