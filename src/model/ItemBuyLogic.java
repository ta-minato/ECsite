package model;

import java.util.List;

import dao.CartDAO;


public class ItemBuyLogic {
	public static List<Item> execute(List<Item> cartList,Account account) {
		CartDAO dao = new CartDAO();
		List<Item> scartList = dao.buyItems(cartList,account);
		return cartList;
	}
}
