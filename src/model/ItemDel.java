package model;

import dao.ItemDAO;

public class ItemDel {
	public void execute(Item item) {
		ItemDAO dao = new ItemDAO();
		dao.delete(item);
	}
}
