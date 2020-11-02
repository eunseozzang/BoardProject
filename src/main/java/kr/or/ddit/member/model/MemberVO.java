package kr.or.ddit.member.model;

public class MemberVO {
	
	private String userid;
	private String username;
	private String userpass;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", username=" + username + ", userpass=" + userpass + "]";
	}
}
