package model;

public class Login {
	private String userid;
	private String pass;

	public Login(String userid,String pass) {
		this.userid = userid;
		this.pass = pass;
	}
	public String getUserid() {return userid;}
	public String getPass() {return pass;}
}
