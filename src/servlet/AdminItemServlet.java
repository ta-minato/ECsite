package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.ItemAdd;
import model.ItemDel;
import model.ItemLogic;
import model.ItemUpd;
import model.MainItem;

/**
 *  AdminItemServlet
 */
@WebServlet("/AdminItemServlet")
public class AdminItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String itemcode = request.getParameter("itemcode");
		//商品データを取得して存在チェック
		ItemLogic itemLogic = new ItemLogic();
		MainItem mainItem = new MainItem("",itemcode);
		Item item = itemLogic.execute(mainItem);

		if (item  == null) {
			//アイテムデータベースに存在しない時の場合（空画面を表示するため）
			item = new Item(itemcode,"",0, 0, 0,0, "");
		}

		//商品情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("item",item);
		//商品新規登録画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/adminItemAdd.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String itemcode = request.getParameter("itemcode");
		String itemname = request.getParameter("itemname");
		int price = Integer.parseInt(request.getParameter("price"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		int sales = 0;
		int restocks = 0;
		String category = request.getParameter("category");
		String button = request.getParameter("button");
		//商品データを取得して存在チェック
		ItemLogic itemLogic = new ItemLogic();
		MainItem mainItem = new MainItem(itemname,itemcode);
		Item item = itemLogic.execute(mainItem);
		//商品データをセッションスコープにを保存
		HttpSession session = request.getSession();
		session.setAttribute("item",item);

		if (item  == null) {
			//アイテムデータベースに存在しない時
			//商品データを新規登録(INSERT）
			//アイテムインスタンスの生成
			item = new Item(itemcode, itemname, price, cost, sales,restocks, category);
			ItemAdd itemAdd = new ItemAdd();
			itemAdd.execute(item);
		}
		else {
			//アイテムデータベースに存在した時
			//商品データを変更登録(UPDATE又はDELET）
			//アイテムインスタンスの生成
			item = new Item(itemcode, itemname, price, cost, sales,restocks, category);
			if(button.equals("登録")) {
				ItemUpd itemUpd = new ItemUpd();
				itemUpd.execute(item);
			}
			else {
				ItemDel itemDel = new ItemDel();
				itemDel.execute(item);
			}
		}

		//商品データをセッションスコープにを保存
		session.setAttribute("item",item);
		session.setAttribute("button",button);
		//商品登録結果画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/itemAddResult.jsp");
		dispatcher.forward(request, response);

	}
}
