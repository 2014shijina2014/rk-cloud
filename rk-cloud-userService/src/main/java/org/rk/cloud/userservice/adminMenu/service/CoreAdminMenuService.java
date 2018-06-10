package org.rk.cloud.userservice.adminMenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.userservice.adminMenu.dao.ICoreAdminMenuDao;
import org.rk.cloud.userservice.user.permission.service.ICorePermService;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.util.RKAssert;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.domain.menu.CoreAdminMenu;
import org.rk.core.jdbc.dao.util.Condition;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CoreAdminMenuService")
public class CoreAdminMenuService extends ModelService<CoreAdminMenu> implements ICoreAdminMenuService {
	@Resource(name = "CorePermService")
	private ICorePermService corePermService;

	private ICoreAdminMenuDao modelDao;

	@Resource(name = "CoreAdminMenuDao")
	public void setCoreAdminMenuDao(ICoreAdminMenuDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao = modelDao;
	}

	@Transactional
	@Override
	public boolean deleteById(Long id) {
		//查出子目录，先删除子目录，再删除其本身
		CoreAdminMenu fatherMenu = selectModel(id);
		deleteBatchByPro("fatherCode", fatherMenu.getCode());
		return modelDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CoreAdminMenu> selectListByPermId(Long permId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permId", permId);
		List<CoreAdminMenu> resultList = selectModelList(params);
		return resultList;
	}

	@Transactional
	@Override
	public Long insertTreeModel(CoreAdminMenu coreAdminMenu) {
		// 获取当前最大的记录
		String maxId = String.valueOf(modelDao.queryMaxId(getPojoClass()));
		if (!RkStrUtil.hasText(coreAdminMenu.getFatherCode())) {
			coreAdminMenu.setFatherCode("-1");// 不指定父节点都是紧接着根节点
			coreAdminMenu.setCode(maxId + "A");
		} else {
			coreAdminMenu.setCode(coreAdminMenu.getFatherCode() + maxId + "A");
		}
		return insertModel(coreAdminMenu);
	}

	@Override
	@Transactional
	public List<CoreAdminMenu> selectMenu(String moduleCode, String fatherCode) {
		RKAssert.hasText(moduleCode, "模块编码不能为空");
		Map<String, Object> params = new HashMap<String, Object>();
		if (RkStrUtil.hasText(moduleCode)) {
			params.put("moduleCode", moduleCode);
		}
		if (RkStrUtil.hasText(fatherCode)) {
			params.put("fatherCode", fatherCode);
		}
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		OrderBean orderBean = new OrderBean("type", Condition.condi_mode_order_desc);
		orderList.add(orderBean);
		List<CoreAdminMenu> menuList = selectModelList(params,orderList);
		List<CoreAdminMenu> newMenuList = new ArrayList<CoreAdminMenu>();
		for (CoreAdminMenu coreAdminMenu : menuList) {
			Long permId = coreAdminMenu.getPermId();
			if (!RkObjectUtil.isEmpty(permId)) {
				CorePerm corePerm = corePermService.selectModel(permId);
				coreAdminMenu.setHUrl(corePerm.getPermUrl());
			}
			newMenuList.add(coreAdminMenu);
		}
		return newMenuList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CoreAdminMenu> selectByListByPermIds(List<Long> permIdList){
		List<CoreAdminMenu> result=modelDao.queryForListByPermIds(permIdList);
		if(!RkObjectUtil.isEmpty(result)){
			return result;
		}
		return Collections.emptyList();
	}
}
