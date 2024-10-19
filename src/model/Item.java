package model;

import java.io.Serializable;

public class Item implements Serializable {
	private String itemcode;
	private String itemname;
	private int price;
	private int cost;
	private int sales;
	private int restocks;
	private int buy;
	private String category;

	public Item() {

	}

	public Item(String itemcode, String itemname, int price, int cost, int sales, int restocks, String category) {
		this.itemcode = itemcode;
		this.itemname = itemname;
		this.price = price;
		this.cost = cost;
		this.sales = sales;
		this.restocks = restocks;
		this.buy = 0;
		this.category = category;
	}
	public String getItemcode() {return itemcode;}
	public String getItemname() {return itemname;}
	public int getPrice() {return price;}
	public int getCost() {return cost;}
	public int getSales() {return sales;}
	public int getRestocks() {return restocks;}
	public int getBuy() {return buy;}
	public String getCategory() {return category;}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public void setRestocks(int restocks) {
		this.restocks = restocks;
	}
	public void setBuy(int buy) {
		this.buy = buy;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
