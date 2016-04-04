package MiniTwitter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class MiniTwitterDAO implements IMiniTwitterDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int signupUser(String uname, String pwd) {
		if (!checkExists(uname))
			return signup(uname, pwd);
		else 
			return -1;
	}

	public List<String> getFollowers(String uname) {
		String query = "SELECT follower FROM follows WHERE following = :uname;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		List<String> followers = null;
		try {
			followers = (List<String>)namedParameterJdbcTemplate.queryForList(query,namedParameters,String.class);
		} catch(Exception e) {
		}		
		return followers;
	}

	public List<String> getFollowings(String uname) {
		String query = "SELECT follower FROM follows WHERE follower = :uname;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		List<String> followers = null;
		try {
			followers = (List<String>)namedParameterJdbcTemplate.queryForList(query,namedParameters,String.class);
		} catch(Exception e) {}		
		return followers;
	}
	
	public void startFollow(String follower, String following) {
		String query = "INSERT INTO follows (follower,following) VALUES (:follower,:following);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("follower", follower);
		namedParameters.put("following", following);
		try {
			namedParameterJdbcTemplate.update(query,namedParameters);
		} catch(Exception e) {}		
	}

	public void unFollow(String follower, String following) {
		String query = "DELETE FROM follows WHERE follower = :follower AND followering = :following);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("follower", follower);
		namedParameters.put("following", following);
		try {
			namedParameterJdbcTemplate.update(query,namedParameters);
		} catch(Exception e) {}			
	}
	
	public void tweetMessage(String uname, String message) {
		String query = "INSERT INTO tweets (uname,tweet) VALUES (:uname,:message);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		namedParameters.put("message", message);
		try {
			namedParameterJdbcTemplate.update(query,namedParameters);
		} catch(Exception e) {}	
	}

	public List<String> getTweet(String uname, String search) {
		String query = "SELECT t.uname,t.tweet FROM tweets t, follows f "
				+ "WHERE ((f.follower = :uname AND f.following = t.uname) OR t.uname = :uname) "
				+ "AND (t.uname = :search OR t.message = :search);";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		namedParameters.put("search", "%"+search+"%");
		List<String> tweets = new ArrayList<String>();
		try {
			List<Map<String, Object>> rows = namedParameterJdbcTemplate.queryForList(query,namedParameters);
			for (Map<String, Object> row : rows) {
				String r = "@" + (String)row.get("uname") + ": " + (String)row.get("tweet");
				tweets.add(r);
			}
		} catch(Exception e) {}		
		return tweets;
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
