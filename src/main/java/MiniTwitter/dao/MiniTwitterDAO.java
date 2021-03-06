package MiniTwitter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import MiniTwitter.object.UserRelation;


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

	public List<UserRelation> findUser(String uname, String search) {
		String query = "SELECT uname FROM users WHERE uname LIKE :search;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("search", "%"+search+"%");
		List<UserRelation> users = new ArrayList<UserRelation>();
		try {
			List<Map<String, Object>> rows = namedParameterJdbcTemplate.queryForList(query,namedParameters);
			for (Map<String, Object> row : rows) {
				String user = (String)row.get("uname");
				int relation = getRelation(uname, user);
				users.add(new UserRelation(user,relation));
			}
		} catch(Exception e) {}		
		return users;
	}
	
	private int getRelation(String uname, String search) {
		if (uname.equals(search))
			return -1;
		String query = "SELECT COUNT(*) FROM follows WHERE follower = :uname AND following = :search;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		namedParameters.put("search", search);
		int rowcount = namedParameterJdbcTemplate.queryForObject(query,namedParameters,Integer.class);
		return rowcount;
	}
	
	public String[] getFollowers(String uname) {
		String query = "SELECT follower FROM follows WHERE following = :uname;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		List<String> followers = null;
		try {
			followers = (List<String>)namedParameterJdbcTemplate.queryForList(query,namedParameters,String.class);
		} catch(Exception e) {}		
		return followers.toArray(new String[0]);
	}

	public String[] getFollowings(String uname) {
		String query = "SELECT following FROM follows WHERE follower = :uname;";
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("uname", uname);
		List<String> followings = null;
		try {
			followings = (List<String>)namedParameterJdbcTemplate.queryForList(query,namedParameters,String.class);
		} catch(Exception e) {}		
		return followings.toArray(new String[0]);
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
		String query = "DELETE FROM follows WHERE follower = :follower AND following = :following;";
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
		String query = "SELECT uname, tweet FROM tweets "
				+ "WHERE uname = :uname AND (uname LIKE :search OR tweet LIKE :search);";
		String query2 = "SELECT t.uname,t.tweet FROM tweets t, follows f "
				+ "WHERE (f.follower = :uname AND f.following = t.uname) "
				+ "AND (t.uname LIKE :search OR t.tweet LIKE :search);";
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
			rows = namedParameterJdbcTemplate.queryForList(query2,namedParameters);
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
