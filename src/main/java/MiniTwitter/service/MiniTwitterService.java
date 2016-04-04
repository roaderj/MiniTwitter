package MiniTwitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import MiniTwitter.dao.IMiniTwitterDAO;
import MiniTwitter.dao.MiniTwitterDAO;

public class MiniTwitterService implements IMiniTwitterService {

	@Autowired
	private IMiniTwitterDAO miniTwitterDAO;
	
	public int signupUser(String uname, String pwd) {
		if (uname == "" || pwd == "") 
			return -2;
		return miniTwitterDAO.signupUser(uname, pwd);
	}

	public void setUserDAO(MiniTwitterDAO userDAO) {
		this.miniTwitterDAO = userDAO;
	}

	public List<String> getFollowers(String uname) {
		return miniTwitterDAO.getFollowers(uname);
	}

	public List<String> getFollowings(String uname) {
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
