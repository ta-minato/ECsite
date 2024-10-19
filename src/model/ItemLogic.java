package model;

import dao.ItemDAO;
public class ItemLogic {
	public Item execute(MainItem mainItem) {
		ItemDAO dao = new ItemDAO();
		Item item = dao.findItem(mainItem);
		return item;
	}
}
