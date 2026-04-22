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
	
	public DatabaseManager () {
		try {
			connect();
			initiateDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void connect() throws SQLException {
		final String DB_URL = String.format(
				"jdbc:mariadb://%s:%d/%s?user=%s&password=%s",
				SERVER, PORT, DATABASE, USERNAME, PASSWORD);

		conn = DriverManager.getConnection(DB_URL);
		System.out.println("Connection to DB established.");
		stmt = conn.createStatement();
	}
	
	public static void initiateDatabase() {
		
		try {
			Statement statement = conn.createStatement();
			
			statement.execute("""
					CREATE TABLE IF NOT EXISTS item (
					id		INT(11) PRIMARY KEY,
					title	VARCHAR(50),
					author VARCHAR(50),
					genre VARCHAR(50),
					publisher VARCHAR (50)
					);
				""");
			
			statement.execute("""
					CREATE TABLE IF NOT EXISTS book (
					id INT(11) PRIMARY KEY,
					isDamaged VARCHAR(1),
					CONSTRAINT chk_isDamaged CHECK (isDamaged IN ('Y', 'N')),
					FOREIGN KEY (id) REFERENCES item(id)
					);
				""");
			
			statement.execute("""
					CREATE TABLE IF NOT EXISTS cd (
					id INT(11) PRIMARY KEY,
					isDamaged VARCHAR(1),
					CONSTRAINT chk_isDamaged CHECK (isDamaged IN ('Y', 'N')),
					FOREIGN KEY (id) REFERENCES item(id)
					);
				""");
			
			statement.execute("""
					CREATE TABLE IF NOT EXISTS ebook (
					id INT(11) PRIMARY KEY,
					fileSize INT(11),
					FOREIGN KEY (id) REFERENCES item(id)
					);
				""");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
