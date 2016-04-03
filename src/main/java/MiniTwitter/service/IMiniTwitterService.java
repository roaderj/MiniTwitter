package MiniTwitter.service;

import MiniTwitter.bean.UserBean;

public interface IMiniTwitterService {
	public abstract boolean authenicateUser(UserBean userBean);
}
