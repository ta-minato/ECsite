package model;

import java.util.List;

import dao.ItemDAO;

public class ItemListLogic {
	public static List<Item> execute(MainItem mainItem) {
		ItemDAO dao = new ItemDAO();
		List<Item> itemList = dao.findAll(mainItem);
		return itemList;
	}
}
