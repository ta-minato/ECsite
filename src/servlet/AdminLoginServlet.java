package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//管理者ログイン画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/adminLogin.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//ログイン情報のリクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		//ユーザId=admin001 パスワード=admin999以外はエラー
		if(userid.equals("admin001") && pass.equals("admin999")) {
			//管理者メイン画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/adminMain.jsp");
			dispatcher.forward(request, response);
		}
		else {
			//エラーメッセージをリクエストスコープに保存
			if(userid.equals("admin001")) {
				request.setAttribute("errorMsg", "パスワードエラーです");
			}
			else {
				request.setAttribute("errorMsg", "管理者のユーザーIDではありません");
			}
			//ユーザー新規登録画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/adminLogin.jsp");
			dispatcher.forward(request, response);
		}
	}
}
