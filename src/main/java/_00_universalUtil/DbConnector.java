package _00_universalUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	private String DBURL = DbLiterals.URL;
	private String DRIVERNAME = DbLiterals.DRIVERNAME;
	private String USER = DbLiterals.USER;
	private String PASSWORD = DbLiterals.PASSWORD;
	private Connection conn;

	public Connection connect() {
		try {
			Class.forName(DRIVERNAME);
			conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("資料庫連線失敗");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("找不到JDBC Driver");
			return null;
		}
	}
	
}
