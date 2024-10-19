package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Account;
import model.Item;

public class CartDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/netshop?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";
	public List<Item> buyItems(List<Item> cartList,Account account) {
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formatNowDate = dtf1.format(nowDate);
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			for(Item item : cartList) {
				//USERLOGSテーブル（買い物履歴）
				//INSERT文の準備
				String spl = "INSERT INTO USERLOGS(USERCODE, ITEMCODE, AMOUNT, TOTALPRICE, DATE) VALUES(?,?,?,?,?)";
				PreparedStatement pstm = conn.prepareStatement(spl);

				pstm.setInt(1, account.getUsercode());
				pstm.setString(2, item.getItemcode());
				pstm.setInt(3, item.getBuy());
				pstm.setInt(4, item.getPrice() * item.getBuy());
				pstm.setString(5, formatNowDate);

				//INSERT文を実行
				int result = pstm.executeUpdate();

				//ITEMSテーブル
				//SELECT文の準備
				String sql = "SELECT * FROM ITEMS WHERE ITEMCODE = ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, item.getItemcode());

				//SELECT文の実行
				ResultSet rs = pStmt.executeQuery();
				int sales = 0;
				if(rs.next()) {
					sales = rs.getInt("SALES");
				//	int sales = rs.getInt("SALES");

					//UPDATE文の準備
					sql = "UPDATE ITEMS SET SALES = ? WHERE ITEMCODE = ? ";
					PreparedStatement pStmt2 = conn.prepareStatement(sql);
					//DBにある売上数と買い物をした個数を足して書き込み
					pStmt2.setInt(1, sales + item.getBuy());
					pStmt2.setString(2, item.getItemcode());

					//UPDATE文の実行
					int result2 = pStmt2.executeUpdate();
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return cartList;
	}
}
