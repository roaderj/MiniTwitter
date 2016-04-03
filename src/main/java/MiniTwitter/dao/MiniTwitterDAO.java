package MiniTwitter.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import MiniTwitter.bean.UserBean;

public class MiniTwitterDAO implements IMiniTwitterDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean authenticateUser(UserBean userBean) {
		String query = "SELECT COUNT(*) FROM login WHERE uname = :uid and password = :pwd";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uid", userBean.getUserId());
		namedParameters.put("pwd", userBean.getPassword());
		int rowcount = namedParameterJdbcTemplate.queryForObject(query,namedParameters,Integer.class);
		return rowcount == 1;
	}

}
