package model;

import java.io.Serializable;

public class Summary implements Serializable {
	private int usercode;
	private String itemcode;
	private String itemname;
	private int amount;
	private int totalprice;
	private String date;

	public Summary() {

	}

	public Summary(int usercode, String itemcode, String itemname,int amount, int totalprice, String date) {
		this.usercode =usercode;
		this.itemcode =itemcode;
		this.itemname =itemname;
		this.amount = amount;
		this.totalprice = totalprice;
		this.date = date;
	}

	public int getUsercode() {	return usercode;	}
	public String getItemcode() {	return itemcode;	}
	public String getItemname() {	return itemname;	}
	public int getAmount() {	return amount;	}
	public int getTotalprice() {	return totalprice;	}
	public String getDate() {	return date;	}

	public void setUsercode( int usercode) {
		this.usercode = usercode;
	}
	public void setItemcode( String itemcode) {
		this.itemcode = itemcode;
	}
	public void setItemname( String itemname) {
		this.itemname = itemname;
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
