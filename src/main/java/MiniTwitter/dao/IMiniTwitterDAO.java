package MiniTwitter.dao;

import javax.sql.DataSource;
import MiniTwitter.bean.UserBean;

public interface IMiniTwitterDAO {
	public abstract void setDataSource(DataSource dataSource);
	public abstract int signupUser(UserBean userbean);
}
