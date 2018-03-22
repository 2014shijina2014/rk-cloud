package  org.rk.cloud.field.user.service;

import  org.rk.cloud.field.user.UserExtProp;
import org.rk.core.pubServer.service.IModelService;


public interface IUserExtPropService extends IModelService<UserExtProp> {

	public boolean deleteById(Long id);
}
