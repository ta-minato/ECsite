package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/netshop?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public Account findByLogin(Login login) {
		Account account = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SELECT文の準備
			String sql = "SELECT USERCODE,USER_ID,PASS,MAIL,NAME,BIRTHDAY,GENDER,ZIPCODE,ADDRESS FROM ACCOUNTS WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,login.getUserid());
			pStmt.setString(2,login.getPass());

			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				//ユーザーが存在したらデータを取得
				//そのユーザーを表すAccountインスタンスを生成
				int usercode = rs.getInt("USERCODE");
				String userid = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				String birthday = rs.getString("BIRTHDAY");
				String gender = rs.getString("GENDER");
				int zipcode = rs.getInt("ZIPCODE");
				String address = rs.getString("ADDRESS");

				account = new Account(usercode,userid,pass,mail,name,birthday,gender,zipcode,address);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}

	public Account findByRegister(Login login) {
		Account account = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,login.getUserid());

			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				int usercode = rs.getInt("USERCODE");
				String userid = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				String birthday = rs.getString("BIRTHDAY");
				String gender = rs.getString("GENDER");
				int zipcode = rs.getInt("ZIPCODE");
				String address = rs.getString("ADDRESS");

				account = new Account(usercode,userid,pass,mail,name,birthday,gender,zipcode,address);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}

	public boolean create(Account account) {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//INSERT文の準備（コードは自動連番なので指定しなくてよい）
			String spl = "INSERT INTO ACCOUNTS(USER_ID, PASS, MAIL, NAME, BIRTHDAY, GENDER, ZIPCODE, ADDRESS) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(spl);

			//INSERT文中の「?」に使用する値を設定してSQL文を完成
			pstm.setString(1, account.getUserid());
			pstm.setString(2, account.getPass());
			pstm.setString(3, account.getMail());
			pstm.setString(4, account.getUsername());
			pstm.setString(5, account.getBirthday());
			pstm.setString(6, account.getGender());
			pstm.setInt(7, account.getZipcode());
			pstm.setString(8, account.getAddress());

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
