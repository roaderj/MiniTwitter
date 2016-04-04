package MiniTwitter.service;

import java.util.List;

import MiniTwitter.dao.MiniTwitterDAO;

public interface IMiniTwitterService {
	public abstract void setMiniTwitterDAO(MiniTwitterDAO miniTwitterDAO);
	public abstract int signupUser(String uname, String pwd);
	public abstract List<String> getFollowers(String uname);
	public abstract List<String> getFollowings(String uname);
	public abstract void startFollow(String follower, String following);
	public abstract void unFollow(String follower, String following);
	public abstract void tweetMessage(String uname, String message);
	public abstract List<String> getTweet(String uname, String search);
}
