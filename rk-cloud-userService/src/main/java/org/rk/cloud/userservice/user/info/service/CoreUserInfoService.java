package org.rk.cloud.userservice.user.info.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.user.info.CoreUserInfo;
import org.rk.cloud.userservice.user.info.dao.ICoreUserInfoDao;
import org.rk.cloud.userservice.user.user.service.ICoreUserService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RKBeanUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.user.CoreUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserInfoService")
public class CoreUserInfoService extends ModelService<CoreUserInfo> implements ICoreUserInfoService{
	@Resource(name = "CoreUserService")
	private ICoreUserService coreUserService;
	
	private ICoreUserInfoDao modelDao;
	@Resource(name="CoreUserInfoDao")
	public void setCoreUserInfoDao(ICoreUserInfoDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	@Override
	@Transactional
	public PageData selectListPage(Map<String, Object> mapObjs, PageData page){
		page=coreUserService.selectModelListPage(mapObjs, page);
		List<Map<String,Object>> dataList=page.getData();
		List<Map<String,Object>> newdataList=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : dataList) {
			Long userId=(Long) map.get("id");
			map.put("password", "*******");
			map.remove("salt");
			List<Map<String, Object>> coreUserInfoList =selectListByUserId(userId);
			for (Map<String, Object> coreUserInfo : coreUserInfoList) {
				map.put((String)coreUserInfo.get("keyCode"), coreUserInfo.get("infoValue"));
			}
			newdataList.add(map);
		}
		page.setData(newdataList);
		return page;
	}
	
	@Override
	@Transactional
	public Map<String,Object> selectUserInfo(Long userId){
		Map<String,Object> userInfoMap=new HashMap<String, Object>();
		CoreUser coreUser=coreUserService.selectModel(userId);
		userInfoMap=RKBeanUtil.convertBeanToMap(coreUser);//基础信息
		//扩展信息
		List<Map<String, Object>> coreUserInfoList =selectListByUserId(userId);
		for (Map<String, Object> coreUserInfo : coreUserInfoList) {
			userInfoMap.put((String)coreUserInfo.get("keyCode"), coreUserInfo.get("infoValue"));
		}
		userInfoMap.put("password", "*******");
		userInfoMap.remove("salt");
		return userInfoMap;
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> selectListByUserId(Long userId){
		Map<String,Object> mapObjs=new HashMap<String, Object>();
		mapObjs.put("userId", userId);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		OrderBean orderMap=new OrderBean("showIndex", Condition.condi_mode_order_asc);
		orderList.add(orderMap);
		List<Map<String, Object>> resultList =modelDao.queryJoinForList(mapObjs, orderList);
		return resultList;
	}
	
	@Override
	@Transactional
	public void updateUserInfo(CoreUserInfo coreUserInfo){
		if(RkObjectUtil.isEmpty(coreUserInfo.getId())){//如果id不存在，检测userId和keyCode，同一个userId和keyCode只有一条记录
			Map<String,Object> mapObjs=new HashMap<String, Object>();
			mapObjs.put("userId", coreUserInfo.getUserId());
			mapObjs.put("keyCode", coreUserInfo.getKeyCode());
			List<CoreUserInfo> result=super.selectModelList(mapObjs);
			if(!RkObjectUtil.isEmpty(result)&&result.size()>0){
				coreUserInfo.setId(result.get(0).getId());
				super.updateModel(coreUserInfo);
			}else{
				super.insertModel(coreUserInfo);
			}
		}
		super.updateModel(coreUserInfo);
	}
}
