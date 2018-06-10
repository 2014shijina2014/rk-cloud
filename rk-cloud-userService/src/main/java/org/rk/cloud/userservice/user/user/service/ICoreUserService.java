/**
 * 
 */
package org.rk.cloud.userservice.user.user.service;

import org.rk.core.common.base.IBaseService;
import org.rk.core.user.user.CoreUser;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICoreUserService extends IBaseService<CoreUser>{

	boolean deleteById(Long id);

	CoreUser selectUserByUserName(String userName);

	CoreUser selectUserByEmail(String email);

	/**
	 * 用户注册
	 * @param coreUser
	 * @return
	 */
	Long registerUser(CoreUser coreUser);

	String login(String username, String password);

}
