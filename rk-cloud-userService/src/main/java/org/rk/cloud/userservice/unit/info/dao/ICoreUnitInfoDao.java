package org.rk.cloud.userservice.unit.info.dao;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.unit.info.CoreUnitInfo;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUnitInfoDao extends IDBDao<CoreUnitInfo> {

	public boolean deleteById(Long id);

	List<Map<String, Object>> queryJoinForList(Map<String,Object> mapObjs, List<OrderBean> orderList);

	PageData queryJoinForPage(Map<String,Object> mapObjs, PageData page, List<OrderBean> orderList);
}
