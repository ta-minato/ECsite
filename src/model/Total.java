package model;

import java.util.List;

public class Total {
	private int total;

	public int excute(List<Item> cartList) {
		int total = 0;
		for(int i = 0; i < cartList.size(); i++) {
			Item item = cartList.get(i);
			total += (item.getBuy() * item.getPrice());
		}
		return total;
	}

	public int getTotal() {return total;}
}
