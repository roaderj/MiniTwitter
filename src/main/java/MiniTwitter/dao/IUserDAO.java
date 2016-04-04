package MiniTwitter.dao;

import javax.sql.DataSource;

public interface IUserDAO {
	public abstract void setDataSource(DataSource dataSource);
	public abstract int signupUser(String uname, String pwd);
}
