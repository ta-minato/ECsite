package model;

import java.util.List;

import dao.SummaryDAO;

public class SummaryLogic {
	//商品別合計
	public static List<Summary> summaryItem(String date,String kbn) {
		SummaryDAO dao = new SummaryDAO();
		String condition = "";
		if(kbn.equals("kanri")) {
			//ユーザーコード=0のデータを抽出(仕入れデータ)
			condition = " AND USERCODE=0";
		}
		else {
			//ユーザーコード≠0のデータを抽出(売上データ)
			condition = " AND USERCODE>0";
		}
		List<Summary> summaryList = dao.findSumItem(date,condition);

		return summaryList;
	}

	//総合計
	public static Summary summaryTotal(String date,String kbn) {
		SummaryDAO dao = new SummaryDAO();
		String condition = "";
		if(kbn.equals("kanri")) {
			//ユーザーコード=0のデータを抽出(仕入れデータ)
			condition = " AND USERCODE=0";
		}
		else {
			//ユーザーコード≠0のデータを抽出(売上データ)
			condition = " AND USERCODE>0";
		}
		Summary summary = dao.findSumTotal(date,condition);

		return summary;
	}

}
