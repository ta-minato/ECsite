package model;

import dao.ItemDAO;

public class ItemDetail {
	public static Item itemDetail(MainItem mainItem) {
		ItemDAO dao = new ItemDAO();
		Item item = dao.findItem(mainItem);
		return item;
	}
}
