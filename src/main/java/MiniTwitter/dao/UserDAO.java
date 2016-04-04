package MiniTwitter.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class UserDAO implements IUserDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int signupUser(String uname, String pwd) {
		if (!checkExists(uname))
			return signup(uname, pwd);
		else 
			return -1;
	}

	private int signup(String uname, String pwd) {
		String query = "INSERT INTO users (uname,password) VALUES (:uname,:pwd);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		namedParameters.put("pwd", pwd);
		try {
			namedParameterJdbcTemplate.update(query,namedParameters);
		} catch(Exception e) {
			return -2;
		}
		return 1;
	}
	
	private boolean checkExists(String uname) {
		String query = "SELECT COUNT(*) FROM users WHERE uname = :uname";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		int rowcount = namedParameterJdbcTemplate.queryForObject(query,namedParameters,Integer.class);
		return rowcount != 0;
	}
}
