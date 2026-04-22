package manager;

import java.sql.*;
import java.util.ArrayList;
import problemdomain.Item;

public class ItemManager {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg211";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	private Connection conn;
	private Statement stmt;

	private ArrayList<Item> items = new ArrayList<>();

	public ItemManager() {
		try {
			connect();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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

	public void loadItems() {
		String sqlStatement = "SELECT * FROM items;";
		items.clear();

		try {
			ResultSet rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				Item item = new Item(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("genre"),
						rs.getString("publisher"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addItem(String itemType, int id, String title, String author, String genre, String publisher) {
		String sqlStmt = "INSERT INTO items (itemType, id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, itemType);
			stmt.setInt(2, id);
			stmt.setString(3, title);
			stmt.setString(4, author);
			stmt.setString(5, genre);
			stmt.setString(6, publisher);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeItem(int id) {
		String sqlStmt = "DELETE FROM items WHERE id = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void displayByCategory(String genre) {
		for (Item item : items) {
			if (item.getGenre().equalsIgnoreCase(genre)) {
				System.out.println(item);
			}
		}
	}

	public void displayAll() {
		for (Item item : items) {
			System.out.println(item);
		}
	}

	public void disconnect() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Connection closed!");
				System.out.println("Goodbye!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editItemTitle(int id, String title) {
		String sqlStmt = "UPDATE items SET title = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, title);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editAuthor(int id, String author) {
		String sqlStmt = "UPDATE items SET author = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, author);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editGenre(int id, String genre) {
		String sqlStmt = "UPDATE items SET genre = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, genre);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editPublisher(int id, String publisher) {
		String sqlStmt = "UPDATE items SET publisher = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, publisher);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editItemType(int id, String itemType) {
		String sqlStmt = "UPDATE items SET itemType = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, itemType);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}