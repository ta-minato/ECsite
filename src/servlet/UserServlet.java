package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountAdd;
import model.Login;
import model.RegisterLogic;
/**
 * UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//ユーザー新規登録画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String username = request.getParameter("username");
		String mail = request.getParameter("mail");
//		String birthday = request.getParameter("birthday");
		String birthday = request.getParameter("birthday").replace("-", "");
		String gender = request.getParameter("gender");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address = request.getParameter("address");

		//ユーザーデータベースを取得
		Login login = new Login(userid,pass);
		RegisterLogic registerLogic = new RegisterLogic();
		Account account = new Account();
		account= registerLogic.execute(login);

		if (account  != null) {
			//ユーザーデータベースに存在時の場合
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "登録済みのユーザーIDです");
			//ユーザー新規登録画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}
		else {
			//ユーザーデータベースに存在しない時の場合
			//ユーザー情報を新規登録(INSERT）
			//Accountインスタンスの生成
			account = new Account(0, userid, pass, username, mail, birthday, gender, zipcode, address);
			AccountAdd accountAdd = new AccountAdd();
			accountAdd.execute(account);
			//ユーザー情報をセッションスコープにを保存
			HttpSession session = request.getSession();
			session.setAttribute("account",account);
			//ユーザー登録完了画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
