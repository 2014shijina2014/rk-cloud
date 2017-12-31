package org.rk.cloud.sysservice.dictionary.service;

import java.util.List;

import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
import org.rk.core.pubServer.service.IModelService;



public interface ICoreSysDictionaryService extends IModelService<CoreSysDictionary> {

	public boolean deleteById(Long id);

	Long insertTreeModel(CoreSysDictionary coreSysDictionary);

	List<CoreSysDictionary> selectListByDicCode(String dicCode);

	CoreSysDictionary selectListByDicCodeAndDicValue(String dicCode, String dicValue);
}
