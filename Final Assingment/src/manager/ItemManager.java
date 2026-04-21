package manager;

import java.sql.*;
import java.util.Scanner;

public class ItemManager {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg211";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	
	
	private Connection conn;
	private Statement stmt;
	
	
	public ItemManager()
	{
		try
		{
			connect();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	private void connect() throws SQLException
	{
		final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
		conn = DriverManager.getConnection(DB_URL);
		System.out.println("Connection to DB established.");
		stmt= conn.createStatement();	
	}
	
	
	// dont forget to add error handling
	// also needs to be improved
	void addItem(String itemType, int id, String title, String author, String genre, String publisher) {

		
		String sqlStmt = "INSERT INTO items (itemType, id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, itemType);
			stmt.setInt(2, id);
			stmt.setString(3, title);
			stmt.setString(4, author);
			stmt.setString(5, genre);
			stmt.setString(6, publisher);
			// Execute the insert operation
	        stmt.executeUpdate();
			
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	void removeItem(int id) {
		String sqlStmt = "DELETE FROM items WHERE id = ?;";
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void displayByCategory() {
		
	}
	
	void disconnect()
	{
		try
		{
			conn.close();
			System.out.println("Connection closed!");
			System.out.println("Goodbye!");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//edit item
	public void editItemTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	public void editAuthor(String author) {
		// TODO Auto-generated method stub
		
	}

	public void editGenre(String genre) {
		// TODO Auto-generated method stub
		
	}

	public void editPublisher(String publisher) {
		// TODO Auto-generated method stub
		
	}

	public void editItemType(String itemType) {
		// TODO Auto-generated method stub
		
	}

	public void displayAll() {
		// TODO Auto-generated method stub
		
	}
}
