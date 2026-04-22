package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg211";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	private static Connection conn;
	private Statement stmt;

	public DatabaseManager() {
		try {
			conn = getConnection();
			stmt = conn.createStatement();;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		final String DB_URL = String.format(
				"jdbc:mariadb://%s:%d/%s?user=%s&password=%s",
				SERVER, PORT, DATABASE, USERNAME, PASSWORD);

		conn = DriverManager.getConnection(DB_URL);
		System.out.println("Connection to DB established.");
		return conn;
	}

	void createItemTable() {
		
	}
	
	void createRentalTable() {
		
	}
	
	void createUserTable() {
		
	}
}
