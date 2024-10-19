package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.ItemDetail;
import model.MainItem;

/**
 * ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String itemname = request.getParameter("itemname");
		String itemcode = request.getParameter("itemcode");

		//Itemインスタンスを生成しセッションスコープに保存
		MainItem mainItem = new MainItem(itemname,itemcode);
		Item item = ItemDetail.itemDetail(mainItem);

		HttpSession session = request.getSession();
		session.setAttribute("item", item);

		//商品詳細画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/item.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String itemcode = request.getParameter("itemcode");
		int buy = Integer.parseInt(request.getParameter("buy"));

		HttpSession session = request.getSession();
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");

		String url = "";

		if(buy > 0 ) {//個数選択数が1以上の場合
			int flg = 0;
			for(int i = 0; i < cartList.size(); i++) {
				Item item = cartList.get(i);
				if(itemcode.equals(item.getItemcode())) {//カートにitemcodeが一致する商品がある場合
					flg = 1;
					//cartList内の個数を変更
					buy += item.getBuy();
					if(buy <= item.getRestocks() - item.getSales()) {
						item.setBuy(buy);
						session.setAttribute("item",item);
						cartList.set(i,item);
						session.setAttribute("cartList",cartList);
						url = "WEB-INF/jsp/itemAdd.jsp";
					}else {
						//エラーメッセージをリクエストスコープに保存
						request.setAttribute("errorMsg", "※在庫数が不足しています");
						url = "WEB-INF/jsp/item.jsp";
					}
				}
			}if(flg == 0) {//カートにitemcodeが一致する商品が無かった場合
				Item item = (Item)session.getAttribute("item");
				if(buy <= item.getRestocks() - item.getSales()) {//在庫数をチェック
					//セッションスコープのItemインスタンスを呼び出し個数を追加し再保存する
					item.setBuy(buy);
					session.setAttribute("item",item);
						//cartListにItemインスタンスを追加
					cartList.add(item);
					session.setAttribute("cartList",cartList);
					url = "WEB-INF/jsp/itemAdd.jsp";
				}else {//在庫切れ
					//エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "※在庫数が不足しています");
					url = "WEB-INF/jsp/item.jsp";
				}
			}
		}else {//個数選択数が0以下の場合
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "※個数が選択されていません");
			url = "WEB-INF/jsp/item.jsp";
		}
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
