package org.rk.cloud.field.cms.property.service;

import org.rk.cloud.field.cms.property.CmsProperty;
import org.rk.core.pubServer.service.IModelService;


public interface ICmsPropertyService extends IModelService<CmsProperty> {

	public boolean deleteById(Long id);
}
