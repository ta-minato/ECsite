package model;

import java.io.Serializable;

public class UserLog implements Serializable {
	private int num;
	private int usercode;
	private String itemcode;
	private int amount;
	private int totalprice;
	private String date;

	public UserLog() {

	}

	public UserLog(int num, int usercode, String itemcode, int amount, int totalprice, String date) {
		this.num =num;
		this.usercode =usercode;
		this.itemcode =itemcode;
		this.amount = amount;
		this.totalprice = totalprice;
		this.date = date;
	}

	public int getNum() {	return num;	}
	public int getUsercode() {	return usercode;	}
	public String getItemcode() {	return itemcode;	}
	public int getAmount() {	return amount;	}
	public int getTotalprice() {	return totalprice;	}
	public String getDate() {	return date;	}

	public void setNum( int num) {
		this.num = num;
	}
	public void setUsercode( int usercode) {
		this.usercode = usercode;
	}
	public void setItemcode( String itemcode) {
		this.itemcode = itemcode;
	}
	public void setAmount( int amount) {
		this.amount = amount;
	}
	public void setTotalprice( int totalprice) {
		this.totalprice = totalprice;
	}
	public void setDate( String date) {
		this.date = date;
	}
}
