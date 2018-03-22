package  org.rk.cloud.field.user.service;

import javax.annotation.Resource;

import  org.rk.cloud.field.user.UserExtProp;
import  org.rk.cloud.field.user.dao.IUserExtPropDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserExtPropService")
public class UserExtPropService extends ModelService<UserExtProp> implements IUserExtPropService{

	private IUserExtPropDao modelDao;
	@Resource(name="UserExtPropDao")
	public void setUserExtPropDao(IUserExtPropDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
