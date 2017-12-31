package  org.rk.cloud.userservice.userThird.service;

import javax.annotation.Resource;

import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.cloud.userservice.userThird.dao.ICoreUserThirdDao;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserThirdService")
public class CoreUserThirdService extends ModelService<CoreUserThird> implements ICoreUserThirdService{

	private ICoreUserThirdDao modelDao;
	@Resource(name="CoreUserThirdDao")
	public void setCoreUserThirdDao(ICoreUserThirdDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
}
