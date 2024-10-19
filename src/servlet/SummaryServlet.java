package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Summary;
import model.SummaryLogic;

/**
 * SummaryServlet
 */
@WebServlet("/SummaryServlet")
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String kbn =request.getParameter("kbn");
		//区分をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("kbn",kbn);
		//年月を初期設定するためにセッションスコープに保存
		Date dt = new Date();
		SimpleDateFormat sf =  new SimpleDateFormat("yyyy");
		String str = sf.format(dt);
		session.setAttribute("year",str);
		sf =  new SimpleDateFormat("MM");
		str = sf.format(dt);
		session.setAttribute("month",str);
		//月別商品集計画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/Summary.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//区分をセッションスコープより取得
		HttpSession session = request.getSession();
		String kbn = (String) session.getAttribute("kbn");
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		//年月をセッションスコープに保存
		String  sYear = request.getParameter("year");
		String sMonth =request.getParameter("month");
		session.setAttribute("year",sYear);
		session.setAttribute("month",sMonth);

		int year = Integer.parseInt(request.getParameter("year"));
		int month =Integer.parseInt(request.getParameter("month"));
		String date = String.format("%04d", new Integer(year)) + String.format("%02d", new Integer(month));
		//集計リストを取得してリクエストスコープに保存
		//商品別集計
		SummaryLogic summaryLogic = new SummaryLogic();
		List<Summary> summaryLists = SummaryLogic.summaryItem(date,kbn);
		request.setAttribute("summaryLists", summaryLists);
		//総合計
		Summary summary = SummaryLogic.summaryTotal(date,kbn);
		request.setAttribute("summary", summary);

		//月別商品集計画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/Summary.jsp");
		dispatcher.forward(request, response);

	}

}
