/**
 * @Copyright caorendao187@163.com
 * @author cavion(曹仁道)
 */
package org.rk.cloud.userservice.user.userAddress.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.userAddress.CoreUserAddress;
import org.rk.cloud.userservice.user.userAddress.service.ICoreUserAddressService;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
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
@RequestMapping("/rk/web/core/user/userAddress/")
public class CoreUserAddressWeb extends BaseController<CoreUserAddress> {
	@Resource(name = "CoreUserAddressService")
	private ICoreUserAddressService coreUserAddressService;

	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public @ResponseBody Object queryPage(int start, int length, int draw, String area, String address, String recivor) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", "%" + area + "%");
		params.put("address", "%" + address + "%");
		params.put("recivor", "%" + recivor + "%");
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("isDefault", Condition.condi_mode_order_desc);
		orderList.add(orderMap);
		page = coreUserAddressService.selectModelListPage(params, page,orderList);
		return page;
	}
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public @ResponseBody Object queryList(String area, String address, String recivor) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", "%" + area + "%");
		params.put("address", "%" + address + "%");
		params.put("recivor", "%" + recivor + "%");
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("isDefault", Condition.condi_mode_order_desc);
		orderList.add(orderMap);
		List<CoreUserAddress> result = coreUserAddressService.selectModelList(params, orderList);
		return result;
	}

	@RequestMapping(value = "queryByUser", method = RequestMethod.POST)
	public @ResponseBody Object queryByUser() {
		if(RkObjectUtil.isEmpty(SecurityUtil.getUserId())){
			return ajaxError("请先登陆");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", SecurityUtil.getUserId());
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("isDefault", Condition.condi_mode_order_desc);
		orderList.add(orderMap);
		List<CoreUserAddress> result = coreUserAddressService.selectModelList(params, orderList);
		return result;
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody Object query(Long id) {
		CoreUserAddress userAddress = coreUserAddressService.selectModel(id);
		return userAddress;
	}

	public ICoreUserAddressService getCoreUserAddressService() {
		return coreUserAddressService;
	}

}
