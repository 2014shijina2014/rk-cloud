package  org.rk.cloud.field.user.dao;

import javax.annotation.Resource;

import  org.rk.cloud.field.user.UserExtProp;
import org.rk.core.jdbc.dao.DBDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("UserExtPropDao")
public class UserExtPropDao extends DBDao<UserExtProp> implements IUserExtPropDao{

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	@Override
	public boolean deleteById(Long id){
		UserExtProp model=new UserExtProp();
		model.setId(id);
		return super.deleteModel(model);
	}
}
