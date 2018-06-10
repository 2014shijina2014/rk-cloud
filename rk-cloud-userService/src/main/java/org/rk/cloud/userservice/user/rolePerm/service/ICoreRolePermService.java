/**
 * 
 */
package org.rk.cloud.userservice.user.rolePerm.service;

import java.util.List;

import org.rk.core.common.base.IBaseService;
import org.rk.core.user.rolePerm.CoreRolePerm;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreRolePermService extends IBaseService<CoreRolePerm>{

	boolean deleteById(Long id);

	List<CoreRolePerm> selectByRoleId(long roleId);

	Long inserRolePerm(CoreRolePerm coreRolePerm);

	List<CoreRolePerm> selectByListByRoleIds(List<Long> roleIdList);

}
