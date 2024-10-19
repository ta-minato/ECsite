package model;

public class MainItem {
	private String itemname;
	private String itemcode;

	public MainItem() {

	}

	public MainItem(String itemname,String itemcode) {
		this.itemname = itemname;
		this.itemcode = itemcode;
	}
	public String getItemname() {return itemname;}
	public String getItemcode() {return itemcode;}
}
