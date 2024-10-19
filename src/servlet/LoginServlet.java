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

import model.Account;
import model.Item;
import model.ItemListLogic;
import model.Login;
import model.LoginLogic;
import model.MainItem;

/**
 * LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープの初期化（破棄）
		HttpSession session = request.getSession();
		session.invalidate();
		//ログイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");

		//ログイン処理
		Login login = new Login(userid,pass);
		LoginLogic bo = new LoginLogic();
		Account account = bo.execute(login);


		if(account != null) {
		//セッションスコープにaccountインスタンス・itemList・cartListを保存
		HttpSession session = request.getSession();
		session.setAttribute("account", account);

		MainItem mainItem = new MainItem();
		List<Item> itemList = ItemListLogic.execute(mainItem);
		session.setAttribute("itemList", itemList);

		List<Item> cartList = new ArrayList<>();
		session.setAttribute("cartList", cartList);

		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "※ログイン情報が間違っています");
			//ログイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
