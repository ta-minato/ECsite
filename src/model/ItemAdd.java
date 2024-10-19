package model;

import dao.ItemDAO;

public class ItemAdd {
	public void execute(Item item) {
		ItemDAO dao = new ItemDAO();
		dao.create(item);
	}
}
