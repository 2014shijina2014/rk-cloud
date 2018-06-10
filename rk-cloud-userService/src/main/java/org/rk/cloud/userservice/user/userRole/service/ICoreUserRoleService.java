/**
 * 
 */
package org.rk.cloud.userservice.user.userRole.service;

import java.util.List;

import org.rk.core.common.base.IBaseService;
import org.rk.core.user.userRole.CoreUserRole;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreUserRoleService extends IBaseService<CoreUserRole>{

	boolean deleteById(Long id);

	List<CoreUserRole> selectByUserNum(String userNum);

}
