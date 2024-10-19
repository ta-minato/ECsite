package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Summary;



public class SummaryDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/netshop?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	//商品別集計
	public List<Summary> findSumItem(String date,String condition) {
		List<Summary> summaryList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			//SELECT文の準備
			String sql = "SELECT U.ITEMCODE,SUM(U.AMOUNT) AS AMOUNT, SUM(U.TOTALPRICE) AS TOTALPRICE, I.ITEMNAME FROM USERLOGS AS U" +
					" INNER JOIN ITEMS AS I ON I.ITEMCODE=U.ITEMCODE" +
					" WHERE SUBSTRING(U.DATE,1,6)=?" +
					condition +
					" GROUP BY U.ITEMCODE,I.ITEMNAME;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の「?」に使用する値を設定してSQL文を完成
			pStmt.setString(1, date);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				//集計データが存在したらデータを取得
				//Summaryインスタンスを生成しsummaryListへ追加
				String itemcode = rs.getString("ITEMCODE");
				String itemname = rs.getString("ITEMNAME");
				int amount = rs.getInt("AMOUNT");
				int totalprice = rs.getInt("TOTALPRICE");
				Summary summary = new Summary(0,itemcode,itemname,amount,totalprice,null);
				summaryList.add(summary);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return summaryList;
	}
	//総合計
	public Summary findSumTotal(String date,String condition) {
		Summary summary = new Summary();
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			//SELECT文の準備
			String sql = "SELECT SUM(U.AMOUNT) AS AMOUNT, SUM(U.TOTALPRICE) AS TOTALPRICE FROM USERLOGS AS U" +
					" INNER JOIN ITEMS AS I ON I.ITEMCODE=U.ITEMCODE" +
					" WHERE SUBSTRING(U.DATE,1,6)=?" + condition;

			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の「?」に使用する値を設定してSQL文を完成
			pStmt.setString(1, date);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				//集計データが存在したらデータを取得
				//UserLogインスタンスを生成
				int amount = rs.getInt("AMOUNT");
				int totalprice = rs.getInt("TOTALPRICE");
				summary = new  Summary(0,null,null,amount,totalprice,null);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return summary;
	}

}
