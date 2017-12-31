package  org.rk.cloud.userservice.userThird.dao;

import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.core.jdbc.dao.IDBDao;
public interface ICoreUserThirdDao extends IDBDao<CoreUserThird> {

	public boolean deleteById(Long id);
}
