package model;

import dao.ItemDAO;

public class ItemUpd {
	public void execute(Item item) {
		ItemDAO dao = new ItemDAO();
		dao.update(item);
	}
}
