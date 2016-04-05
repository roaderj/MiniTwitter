package MiniTwitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import MiniTwitter.dao.IMiniTwitterDAO;
import MiniTwitter.dao.MiniTwitterDAO;
import MiniTwitter.object.UserRelation;

public class MiniTwitterService implements IMiniTwitterService {

	@Autowired
	private IMiniTwitterDAO miniTwitterDAO;
	
	public int signupUser(String uname, String pwd) {
		if (uname == "" || pwd == "") 
			return -2;
		return miniTwitterDAO.signupUser(uname, pwd);
	}

	public void setMiniTwitterDAO(MiniTwitterDAO miniTwitterDAO) {
		this.miniTwitterDAO = miniTwitterDAO;
	}

	public List<UserRelation> findUser(String uname, String search) {
		return miniTwitterDAO.findUser(uname, search);
	}

	public String[] getFollowers(String uname) {
		return miniTwitterDAO.getFollowers(uname);
	}

	public String[] getFollowings(String uname) {
		return miniTwitterDAO.getFollowings(uname);
	}

	public void startFollow(String follower, String following) {
		miniTwitterDAO.startFollow(follower, following);
	}

	public void unFollow(String follower, String following) {
		miniTwitterDAO.unFollow(follower, following);
	}

	public void tweetMessage(String uname, String message) {
		miniTwitterDAO.tweetMessage(uname, message);
	}

	@Override
	public List<String> getTweet(String uname, String search) {
		return miniTwitterDAO.getTweet(uname, search);
	}
	
	

}
