package  org.rk.cloud.userservice.userThird.service;

import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.core.pubServer.service.IModelService;


public interface ICoreUserThirdService extends IModelService<CoreUserThird> {

	public boolean deleteById(Long id);
}
