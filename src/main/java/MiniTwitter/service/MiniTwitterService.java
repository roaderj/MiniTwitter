package MiniTwitter.service;

import org.springframework.beans.factory.annotation.Autowired;

import MiniTwitter.bean.UserBean;
import MiniTwitter.dao.IMiniTwitterDAO;

public class MiniTwitterService implements IMiniTwitterService {

	@Autowired
	private IMiniTwitterDAO MiniTwitterDAO;
	
	public boolean authenicateUser(UserBean userBean) {
		return MiniTwitterDAO.authenticateUser(userBean);
	}

}
