package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.ItemListLogic;
import model.MainItem;

/**
 * FinalServlet
 */
@WebServlet("/FinalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからcartListを削除し再保存
		HttpSession session = request.getSession();
		session.removeAttribute("cartList");
		List<Item> cartList = new ArrayList<>();
		session.setAttribute("cartList", cartList);

		//セッションスコープにitemListを保存
		MainItem mainItem = new MainItem();
		List<Item> itemList = ItemListLogic.execute(mainItem);
		session.setAttribute("itemList", itemList);

		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}