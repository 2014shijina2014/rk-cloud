/**
 * 
 */
package org.rk.cloud.userservice.domain.service;

import java.util.Map;

import org.rk.core.common.base.IBaseService;
import org.rk.core.common.bean.PageData;
import org.rk.core.domain.domain.CoreDomain;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreDomainService extends IBaseService<CoreDomain>{

	boolean deleteById(Long id);

	PageData selectListPage(Map<String, Object> mapObjs, PageData page);

}
