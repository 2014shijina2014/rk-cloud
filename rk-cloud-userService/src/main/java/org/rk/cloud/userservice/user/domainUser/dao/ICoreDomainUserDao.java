package org.rk.cloud.userservice.user.domainUser.dao;

import java.util.List;
import java.util.Map;

import org.rk.cloud.userservice.user.domainUser.CoreDomainUser;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreDomainUserDao extends IDBDao<CoreDomainUser> {

	public boolean deleteById(Long id);

	List<Map<String, Object>> queryJoinForList(Map<String,Object> mapObjs, List<OrderBean> orderList);

	PageData queryJoinForPage(Map<String,Object> mapObjs, PageData page, List<OrderBean> orderList);
}
