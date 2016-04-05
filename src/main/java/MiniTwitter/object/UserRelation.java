package MiniTwitter.object;

public class UserRelation {
	private String uname;
	/*
	 * 0: unfollow
	 * 1: following
	 * -1: self
	 */
	private int relation;
	public UserRelation(String uname, int relation) {
		this.uname = uname;
		this.relation = relation;
	}
	
	public String getUsername() {
		return uname;
	}
	
	public int getRelation() {
		return relation;
	}
}
