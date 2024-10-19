package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.ItemLogic;
import model.ItemUpd;
import model.MainItem;
import model.UserLog;
import model.UserLogAdd;

/**
 *  ItemOrderServlet
 */
@WebServlet("/ItemOrderServlet")
public class ItemOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String order = request.getParameter("order");
		//商品データを取得して存在チェック
		ItemLogic itemLogic = new ItemLogic();
		MainItem mainItem = new MainItem("",order);
		Item item = itemLogic.execute(mainItem);
		//商品データをセッションスコープにを保存
		HttpSession session = request.getSession();
		session.setAttribute("item",item);

		if (item  == null) {
			//アイテムデータベースに存在しない時エラー
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "削除された商品コードです");
			//アイテムインスタンスの生成(削除されている画面）
			item = new Item(order ,"", 0, 0, 0,0, "");
			session.setAttribute("item",item);
		}

		//商品発注画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/itemOrder.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int restocks = Integer.parseInt(request.getParameter("restocks"));
		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		Item item =(Item)session.getAttribute("item");

		//商品データを取得して存在チェック
		ItemLogic itemLogic = new ItemLogic();
		MainItem mainItem = new MainItem(item.getItemname(),item.getItemcode());
		item = itemLogic.execute(mainItem);

		if (item  != null) {
			//アイテムデータベースに存在する時
			//商品データを変更登録(UPDATE）
			//仕入れ数をセット
			item.setRestocks(restocks + item.getRestocks());
			ItemUpd itemUpd = new ItemUpd();
			itemUpd.execute(item);
			//商品データをセッションスコープにを保存
			session.setAttribute("item",item);
			//買い物履歴データベースに登録（管理者発注履歴はユーザーコード=0とする）
			Date dt = new Date();
			SimpleDateFormat sf =  new SimpleDateFormat("yyyyMMdd");
			String str = sf.format(dt);
			UserLog userLog = new UserLog(0, 0, item.getItemcode(), restocks, item.getCost() * restocks,  str );
			UserLogAdd userLogAdd = new UserLogAdd();
			userLogAdd.execute(userLog);
		}
		else {
			//アイテムデータベースに存在しない時エラー
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "商品が削除されています");
			//商品発注画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/itemOrder.jsp");
			dispatcher.forward(request, response);
		}

		//商品発注結果画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/orderResult.jsp");
		dispatcher.forward(request, response);

	}

}
