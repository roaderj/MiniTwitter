package MiniTwitter.dao;

import java.util.List;

import javax.sql.DataSource;

import MiniTwitter.object.UserRelation;

public interface IMiniTwitterDAO {
	public abstract void setDataSource(DataSource dataSource);
	public abstract int signupUser(String uname, String pwd);
	public abstract List<UserRelation> findUser(String uname, String search);
	public abstract List<String> getFollowers(String uname);
	public abstract List<String> getFollowings(String uname);
	public abstract void startFollow(String follower, String following);
	public abstract void unFollow(String follower, String following);
	public abstract void tweetMessage(String uname, String message);
	public abstract List<String> getTweet(String uname, String search);
}
