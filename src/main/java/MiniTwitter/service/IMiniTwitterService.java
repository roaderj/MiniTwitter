package MiniTwitter.service;

import java.util.List;

import MiniTwitter.dao.MiniTwitterDAO;
import MiniTwitter.object.UserRelation;

public interface IMiniTwitterService {
	public abstract void setMiniTwitterDAO(MiniTwitterDAO miniTwitterDAO);
	public abstract int signupUser(String uname, String pwd);
	public abstract List<UserRelation> findUser(String uname, String search);
	public abstract String[] getFollowers(String uname);
	public abstract String[] getFollowings(String uname);
	public abstract void startFollow(String follower, String following);
	public abstract void unFollow(String follower, String following);
	public abstract void tweetMessage(String uname, String message);
	public abstract List<String> getTweet(String uname, String search);
}
