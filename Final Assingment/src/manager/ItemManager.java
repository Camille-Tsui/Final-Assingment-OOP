package manager;

import problemdomain.Book;
import problemdomain.CD;
import problemdomain.EBook;

import java.sql.*;
import java.util.ArrayList;

import exception.ItemNotFoundException;
import problemdomain.Item;

public class ItemManager {
	Connection conn;
	Statement stmt;

	private ArrayList<Item> items = new ArrayList<>();

	public ItemManager() {
		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.createStatement();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	public void loadItems() {
		items.clear();
		String sqlStatement;
		ResultSet rs;
		try {
			//loading books first
			sqlStatement = "SELECT i.*, b.isDamaged FROM item i RIGHT JOIN book b ON i.id = b.id;";
			rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				boolean isDamaged;
				if (rs.getString("isDamaged") == "Y") {
					isDamaged = true;
				}
				else {
					isDamaged = false;
				}
				
				Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("genre"),
						rs.getString("publisher"),
						isDamaged
						);
				items.add(book);
			}
			
			//loading CDs
			sqlStatement = "SELECT i.*, c.isDamaged FROM item i RIGHT JOIN cd c ON i.id = c.id;";
			rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				boolean isDamaged;
				if (rs.getString("isDamaged") == "Y") {
					isDamaged = true;
				}
				else {
					isDamaged = false;
				}
				
				CD cd = new CD(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("genre"),
						rs.getString("publisher"),
						isDamaged
						);
				items.add(cd);
			}
			
			//loading eBooks
			sqlStatement = "SELECT i.*, e.fileSize FROM item i RIGHT JOIN ebook e ON i.id = e.id;";
			rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				
				EBook eBook = new EBook(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("genre"),
						rs.getString("publisher"),
						rs.getInt("fileSize")
						);
				items.add(eBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//add book
	public void addBook(int id, String title, String author, String genre, String publisher) {
		String itemsqlStmt = "INSERT INTO item (id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?);";
		String booksqlStmt =  "INSERT INTO book (id, isDamaged) VALUES (?, ?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(itemsqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, title);
			stmt.setString(3, author);
			stmt.setString(4, genre);
			stmt.setString(5, publisher);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(booksqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, "N");
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//add eBook
	public void addEBook(int id, String title, String author, String genre, String publisher, int fileSize) {
		String itemsqlStmt = "INSERT INTO item (id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?);";
		String ebooksqlStmt =  "INSERT INTO ebook (id, fileSize) VALUES (?, ?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(itemsqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, title);
			stmt.setString(3, author);
			stmt.setString(4, genre);
			stmt.setString(5, publisher);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(ebooksqlStmt);
			stmt.setInt(1, id);
			stmt.setInt(2, fileSize);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//add CD
	public void addCD(int id, String title, String author, String genre, String publisher) {
		String itemsqlStmt = "INSERT INTO item (id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?);";
		String cdsqlStmt =  "INSERT INTO cd (id, isDamaged) VALUES (?, ?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(itemsqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, title);
			stmt.setString(3, author);
			stmt.setString(4, genre);
			stmt.setString(5, publisher);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(cdsqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, "N");
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeItem(int id) {
		String itemType = getItemType(id).toLowerCase();
		String itemsqlStmt = "DELETE FROM item WHERE id = ?;";
		String categorysqlStmt = "DELETE FROM " + itemType + " WHERE id = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(categorysqlStmt);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt = conn.prepareStatement(itemsqlStmt);
			stmt.setInt(1,id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void displayByCategory(String category) {
		for (Item item : items) {
			if (item.getClass().getSimpleName().equalsIgnoreCase(category)) {
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
		String sqlStmt = "UPDATE item SET title = ? WHERE id = ?";
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
		String sqlStmt = "UPDATE item SET author = ? WHERE id = ?";
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
		String sqlStmt = "UPDATE item SET genre = ? WHERE id = ?";
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
		String sqlStmt = "UPDATE item SET publisher = ? WHERE id = ?";
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
		String sqlStmt = "UPDATE item SET itemType = ? WHERE id = ?";
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
	
	public void editDamaged(int id, String damaged) {
		String itemType = getItemType(id).toLowerCase();
		
		String sqlStmt;
		sqlStmt = "UPDATE " + itemType + "SET isDamaged = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, damaged);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			loadItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getItemType(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item.getClass().getSimpleName();
			}
		}
		//throw new ItemNotFoundException("Item not found");
		return null;
	}
}