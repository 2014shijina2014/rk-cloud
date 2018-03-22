package org.rk.cloud.field.cms.progProp.dao;

import java.util.List;
import java.util.Map;

import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICmsProgPropDao extends IDBDao<CmsProgProp> {

	public boolean deleteById(Long id);

	PageData queryJoinForPage(Map<String,Object> mapObjs, PageData page, List<OrderBean> orderList);

	List<Map<String, Object>> queryJoinForList(Map<String,Object> mapObjs, List<OrderBean> orderList);
}
