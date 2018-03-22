package org.rk.cloud.field.cms.programa.service;

import org.rk.cloud.field.cms.programa.CmsPrograma;
import org.rk.core.pubServer.service.IModelService;

public interface ICmsProgramaService extends IModelService<CmsPrograma> {

	public boolean deleteById(Long id);

	Long insertTreeModel(CmsPrograma pubPrograma);
}
