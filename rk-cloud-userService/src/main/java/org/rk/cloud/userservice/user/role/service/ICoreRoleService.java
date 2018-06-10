/**
 * 
 */
package org.rk.cloud.userservice.user.role.service;

import org.rk.core.common.base.IBaseService;
import org.rk.core.user.userRole.CoreRole;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreRoleService extends IBaseService<CoreRole>{

	boolean deleteById(Long id);

	Long insertTreeModel(CoreRole coreRole);

}
