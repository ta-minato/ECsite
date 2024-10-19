package model;

import java.io.Serializable;

public class Account implements Serializable{
	private int usercode;
	private String userid;
	private String pass;
	private String username;
	private String mail;
	private String birthday;
	private String gender;
	private int zipcode;
	private String address;

	public Account() {

	}

	public Account(int usercode,String userid,String pass,String username, String mail,
			String birthday,String gender,int zipcode,String address) {
	this.usercode = usercode;
	this.userid = userid;
	this.pass = pass;
	this.username = username;
	this.mail     = mail;
	this.birthday = birthday;
	this.gender = gender;
	this.zipcode = zipcode;
	this.address = address;
	}

	public int getUsercode() {return usercode;}
	public String getUserid() {return userid;}
	public String getPass() {return pass;}
	public String getUsername() {return username;}
	public String getMail() {return mail;}
	public String getBirthday() {return birthday;}
	public String getGender() {return gender ;}
	public int getZipcode() {return zipcode;}
	public String getAddress() {return address;}

	public void setUsercode(int usercode) {this.usercode = usercode;}
	public void setUserid(String userid) {this.userid   = userid;}
	public void setPass(String pass) {this.pass = pass;}
	public void setUsername(String username) {this.username = username;}
	public void setMail(String mail) {this.mail = mail;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public void setGender(String gender) {this.gender = gender;}
	public void setZipcode(int zipcode) {this.zipcode = zipcode;}
	public void setAddress(String address) {this.address = address;}
}