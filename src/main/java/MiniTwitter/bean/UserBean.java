package MiniTwitter.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class UserBean {
	
	@NotEmpty(message="Username cannot be empty\n")
	private String userId;
	@NotEmpty(message="Password cannot be empty")
	private String password;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
