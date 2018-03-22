package  org.rk.cloud.field.user.dao;

import  org.rk.cloud.field.user.UserExtProp;
import org.rk.core.jdbc.dao.IDBDao;
public interface IUserExtPropDao extends IDBDao<UserExtProp> {

	public boolean deleteById(Long id);
}
