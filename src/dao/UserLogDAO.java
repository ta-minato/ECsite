package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserLog;

public class UserLogDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/netshop?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public boolean create(UserLog userLog) {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//INSERT文の準備（連番は自動連番なので指定しなくてよい）
			String spl = "INSERT INTO USERLOGS(USERCODE, ITEMCODE, AMOUNT, TOTALPRICE, DATE) VALUES(?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(spl);

			//INSERT文中の「?」に使用する値を設定してSQL文を完成
			pstm.setInt(1, userLog.getUsercode());
			pstm.setString(2, userLog.getItemcode());
			pstm.setInt(3, userLog.getAmount());
			pstm.setInt(4, userLog.getTotalprice());
			pstm.setString(5, userLog.getDate());

			//INSERT文を実行(resultに追加された行数が代入される)
			int result = pstm.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
