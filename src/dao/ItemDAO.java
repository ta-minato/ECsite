package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.MainItem;

public class ItemDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/netshop?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<Item> findAll(MainItem mainItem) {
		List<Item> itemList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//検索ワード
			String sitemname = mainItem.getItemname();
			if(sitemname == null) { sitemname = ""; }

			//SELECT文の準備
			String sql = "SELECT * FROM ITEMS WHERE ITEMNAME LIKE '%" + sitemname +
					"%' OR CATEGORY LIKE '%" + sitemname + "%'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				//アイテムが存在したらデータを取得
				//そのアイテムを表すItemインスタンスを生成しitemListへ追加
				String itemcode = rs.getString("ITEMCODE");
				String itemname = rs.getString("ITEMNAME");
				int price = rs.getInt("PRICE");
				int cost = rs.getInt("COST");
				int sales = rs.getInt("SALES");
				int restocks = rs.getInt("RESTOCKS");
				String category = rs.getString("CATEGORY");
				Item item = new Item(itemcode,itemname,price,cost,sales,restocks,category);
				itemList.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return itemList;
	}

	public Item findItem(MainItem mainItem) {
		Item item = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SELECT文の準備
			String sql = "SELECT * FROM ITEMS WHERE ITEMCODE = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mainItem.getItemcode());
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				//アイテムが存在したらデータを取得
				//そのアイテムを表すItemインスタンスを生成
				String itemcode = rs.getString("ITEMCODE");
				String itemname = rs.getString("ITEMNAME");
				int price = rs.getInt("PRICE");
				int cost = rs.getInt("COST");
				int sales = rs.getInt("SALES");
				int restocks = rs.getInt("RESTOCKS");
				String category = rs.getString("CATEGORY");
				item = new Item(itemcode,itemname,price,cost,sales,restocks,category);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return item;
	}

	public boolean create(Item item) {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//INSERT文の準備（コードは自動連番なので指定しなくてよい）
			String spl = "INSERT INTO ITEMS(ITEMCODE,ITEMNAME,PRICE,COST,SALES,RESTOCKS,CATEGORY) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(spl);

			//INSERT文中の「?」に使用する値を設定してSQL文を完成
			pstm.setString(1, item.getItemcode());
			pstm.setString(2, item.getItemname());
			pstm.setInt(3, item.getPrice());
			pstm.setInt(4, item.getCost());
			pstm.setInt(5, item.getSales());
			pstm.setInt(6, item.getRestocks());
			pstm.setString(7, item.getCategory());

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

	public boolean update(Item item) {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//UPDATE文の準備
			String spl = "UPDATE ITEMS SET "
					+ "ITEMNAME=?"
					+ ",PRICE=?"
					+ ",COST=?"
					+ ",SALES=?"
					+ ",RESTOCKS=?"
					+ ",CATEGORY=?"
					+ " WHERE ITEMCODE=? ";

			PreparedStatement pstm = conn.prepareStatement(spl);

			//UPDATE文中の「?」に使用する値を設定してSQL文を完成
			pstm.setString(1, item.getItemname());
			pstm.setInt(2, item.getPrice());
			pstm.setInt(3, item.getCost());
			pstm.setInt(4, item.getSales());
			pstm.setInt(5, item.getRestocks());
			pstm.setString(6, item.getCategory());
			pstm.setString(7, item.getItemcode());

			//UPDATE文を実行(resultに追加された行数が代入される)
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

	public boolean delete(Item item) {
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//DELETE文の準備
			String spl = "DELETE FROM ITEMS  WHERE ITEMCODE=? ";

			PreparedStatement pstm = conn.prepareStatement(spl);

			//DELETE文中の「?」に使用する値を設定してSQL文を完成
			pstm.setString(1, item.getItemcode());

			//DELETE文を実行(resultに追加された行数が代入される)
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
