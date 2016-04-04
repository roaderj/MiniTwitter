package MiniTwitter.service;

import MiniTwitter.dao.UserDAO;

public interface IMiniTwitterService {
	public abstract void setUserDAO(UserDAO userDAO);
	public abstract int signupUser(String uname, String pwd);
}
