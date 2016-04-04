package MiniTwitter.service;

import org.springframework.beans.factory.annotation.Autowired;

import MiniTwitter.dao.IUserDAO;
import MiniTwitter.dao.UserDAO;

public class MiniTwitterService implements IMiniTwitterService {

	@Autowired
	private IUserDAO userDAO;
	
	public int signupUser(String uname, String pwd) {
		if (uname == "" || pwd == "") 
			return -2;
		return userDAO.signupUser(uname, pwd);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
