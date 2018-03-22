package org.rk.cloud.field.cms.progProp.service;

import java.util.List;
import java.util.Map;

import org.rk.cloud.field.cms.progProp.CmsProgProp;
import org.rk.core.common.bean.PageData;
import org.rk.core.pubServer.service.IModelService;


public interface ICmsProgPropService extends IModelService<CmsProgProp> {

	public boolean deleteById(Long id);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);

	List<Map<String, Object>> selectList(Map<String, Object> mapObjs);
}
