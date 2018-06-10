/**
 * 
 */
package org.rk.cloud.userservice.user.permission.service;

import java.util.List;

import org.rk.core.common.base.IBaseService;
import org.rk.core.user.userPerm.CorePerm;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cavion(曹仁道)
 * @类描述：
 * 2016年12月7日
 */
public interface ICorePermService extends IBaseService<CorePerm>{

	boolean deleteById(Long id);

	CorePerm selectByCode(String permCode);

	List<CorePerm> selectByListByPermIds(List<Long> permIdList);

}
