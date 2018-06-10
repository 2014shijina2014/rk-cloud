/**
 * 
 */
package org.rk.cloud.userservice.module.service;

import java.util.List;

import org.rk.core.common.base.IBaseService;
import org.rk.core.domain.module.CoreModule;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreModuleService extends IBaseService<CoreModule>{

	boolean deleteById(Long id);

	CoreModule selectByCode(String moduleCode);

	CoreModule selectByUniqueId(String uniqueId);

	List<CoreModule> selectByListByModuleCodes(List<String> moduleCodeList);

}
