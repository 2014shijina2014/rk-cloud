/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.infokey.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.infokey.CoreUserInfokey;
import org.rk.cloud.userservice.user.infokey.service.ICoreUserInfokeyService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cavion（曹仁道）
 * @date 2017年2月10日
 */
@Controller
@RequestMapping("/rk/web/core/user/userInfoKey/")
public class CoreUserInfokeyWeb extends BaseController<CoreUserInfokey> {
	@Resource(name = "CoreUserInfokeyService")
	private ICoreUserInfokeyService coreUserInfokeyService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String keyCode, String keyName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyCode", "%" + keyCode + "%");
		params.put("keyName", "%" + keyName + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("showIndex", Condition.condi_mode_order_asc);
		orderList.add(orderMap);
		page = coreUserInfokeyService.selectModelListPage(params, page,orderList);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public @ResponseBody Object queryList(String keyCode, String keyName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyCode", "%" + keyCode + "%");
		params.put("keyName", "%" + keyName + "%");
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("showIndex", Condition.condi_mode_order_asc);
		orderList.add(orderMap);
		List<CoreUserInfokey> result = coreUserInfokeyService.selectModelList(params, orderList);
		return result;
	}

	@RequestMapping(value = "queryByCode", method = RequestMethod.POST)
	public @ResponseBody Object queryByCode(String keyCode) {
		CoreUserInfokey userInfokey = coreUserInfokeyService.selectByCode(keyCode);
		return userInfokey;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUserInfokey userInfokey = coreUserInfokeyService.selectModel(id);
		return userInfokey;
	}

	public ICoreUserInfokeyService getCoreUserInfokeyService() {
		return coreUserInfokeyService;
	}

}
