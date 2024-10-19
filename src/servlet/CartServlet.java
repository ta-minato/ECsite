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

import model.Account;
import model.Item;
import model.ItemBuyLogic;
import model.Total;

/**
 * CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");

		//合計金額の計算
		int total;
		Total to = new Total();
		total = to.excute(cartList);
		String sTotal = String.format("%,d",total);
		request.setAttribute("total", sTotal);

		//カート画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		String itemcode = request.getParameter("itemcode");
		String strbuy = request.getParameter("buy");
		int buy = 0;
		if(strbuy != null) {
		buy = Integer.parseInt(strbuy);
		}

		//セッションスコープからcartList・Accountインスタンスを呼び出す
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");

		int total;
		String url = "";
		int flg = 0;
		//カートに商品がある場合
		if (command == null) { command = ""; }

		if (command.equals("購入")) {//購入処理
			if(cartList.size() == 0) {//カートに商品が無い場合
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "※商品がカートにありません");
				//URLの指定(商品詳細画面)
				url = "WEB-INF/jsp/cart.jsp";
			}else {
				//カート内の商品の在庫をチェック
				for(int i = 0; i < cartList.size(); i++) {//1商品ごと
					Item item = cartList.get(i);
					if(buy <= item.getRestocks() - item.getSales()) {
						flg = 1;
					}else {
						flg = 2;
						break;
					}
				}if(flg == 1) {
					ItemBuyLogic bl = new ItemBuyLogic();
					cartList = bl.execute(cartList,account);
					//合計金額の計算
					Total to = new Total();
					total = to.excute(cartList);
					String sTotal = String.format("%,d",total);
					request.setAttribute("total", sTotal);
					//URLの指定(購入完了画面)
					url = "WEB-INF/jsp/result.jsp";
				}else {//在庫切れ
					//エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "※在庫数が不足しています");
					url = "WEB-INF/jsp/cart.jsp";
				}
			}
		}else if(command.equals("削除")) {//カートからの削除
			//セッションスコープからcartListを呼び出す
			cartList = (List<Item>)session.getAttribute("cartList");

			//cartListからItemを削除
			for(int i = 0; i < cartList.size(); i++) {
				Item item = cartList.get(i);
				if(itemcode.equals(item.getItemcode())) {
					cartList.remove(i);
				}
			}
			session.setAttribute("cartList",cartList);
			//合計金額の計算
			Total to = new Total();
			total = to.excute(cartList);
			String sTotal = String.format("%,d",total);
			request.setAttribute("total", sTotal);
			request.setAttribute("errorMsg", "”カートから商品を削除しました”");
			//URLの指定(カート画面)
			url = "WEB-INF/jsp/cart.jsp";
		}else {//カート内での個数変更
			cartList = (List<Item>) session.getAttribute("cartList");

			for (int i = 0; i < cartList.size(); i++) {
				Item item = cartList.get(i);
				if (item.getItemcode().equals(itemcode)) {
					if(buy > 0) {
						if(buy <= item.getRestocks() - item.getSales()) {
							item.setBuy(buy);
							cartList.set(i,item);
						}else {
							//エラーメッセージをリクエストスコープに保存
							request.setAttribute("errorMsg", "※在庫数が不足しています");
							url = "WEB-INF/jsp/cart.jsp";
						}
					}
					else {//0以下であればカートから削除する
						cartList.remove(i);
					}
				}
			}
			session.setAttribute("cartList", cartList);
			//合計金額の計算
			Total to = new Total();
			total = to.excute(cartList);
			String sTotal = String.format("%,d",total);
			request.setAttribute("total", sTotal);
			//URLの指定(カート画面)
			url = "WEB-INF/jsp/cart.jsp";
		}
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}