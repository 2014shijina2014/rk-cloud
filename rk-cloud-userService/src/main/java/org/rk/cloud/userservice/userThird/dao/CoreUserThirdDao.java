package  org.rk.cloud.userservice.userThird.dao;

import javax.annotation.Resource;

import org.rk.cloud.userservice.userThird.CoreUserThird;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("CoreUserThirdDao")
public class CoreUserThirdDao extends DBDao<CoreUserThird> implements ICoreUserThirdDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		CoreUserThird model=new CoreUserThird();
		model.setId(id);
		return super.deleteModel(model);
	}
}
