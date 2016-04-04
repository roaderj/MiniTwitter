package MiniTwitter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import MiniTwitter.bean.UserBean;

public class MiniTwitterDAO implements IMiniTwitterDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int signupUser(UserBean userBean) {
		if (!checkExists(userBean))
			return signup(userBean);
		else 
			return -1;
	}

	private int signup(UserBean userBean) {
		String query = "INSERT INTO users (uname,password) VALUES (:uid,:pwd);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uid", userBean.getUserId());
		namedParameters.put("pwd", userBean.getPassword());
		try {
			namedParameterJdbcTemplate.update(query,namedParameters);
		} catch(Exception e) {
			return -2;
		}
		return 1;
	}
	
	private boolean checkExists(UserBean userBean) {
		String query = "SELECT COUNT(*) FROM users WHERE uname = :uid";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uid", userBean.getUserId());
		int rowcount = namedParameterJdbcTemplate.queryForObject(query,namedParameters,Integer.class);
		return rowcount != 0;
	}
}
