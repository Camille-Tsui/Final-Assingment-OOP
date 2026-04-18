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
	
	private Scanner keyboard;
	
	public ItemManager()
	{
		try
		{
			connect();
			displayMenu();
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
	
	private void displayMenu() {
		int choice = 0;
		keyboard = new Scanner(System.in);
		
		while (choice != 5) {
			System.out.println("1. Add Item.");
			System.out.println("2. Edit Item.");
			System.out.println("3. Remove Item.");
			System.out.println("4. Display Items by Category.");
			System.out.println("5. Exit.");
			
			choice = Integer.parseInt(keyboard.nextLine());
			switch(choice)
			{
				case 1: 
					addItem();
					break;
				case 2:
					editItem();
					break;
				case 3:
					removeItem();
					break;
				case 4:
					displayByCategory();;
					break;
				case 5:
					disconnect();
					break;
				default:
					System.out.println("Incorrect choice. Enter 1 to 5.");
			}
		}
	}
	
	// dont forget to add error handling
	// also needs to be improved
	private void addItem() {
		System.out.println("Enter item type: ");
		String itemType = keyboard.nextLine();
		System.out.println("Enter id: ");
		long id  = keyboard.nextLong();
		keyboard.nextLine(); // scanner bugs out if this line isnt added
		System.out.println("Enter title: ");
		String title = keyboard.nextLine();
		System.out.println("Enter author: ");
		String author = keyboard.nextLine();
		System.out.println("Enter genre: ");
		String genre  = keyboard.nextLine();
		System.out.println("Enter publisher: ");
		String publisher  = keyboard.nextLine();
		
		String sqlStmt = "INSERT INTO items (itemType, id, title, author, genre, publisher) VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setString(1, itemType);
			stmt.setDouble(2, id);
			stmt.setString(3, title);
			stmt.setString(4, author);
			stmt.setString(5, genre);
			stmt.setString(6, publisher);
			// Execute the insert operation
	        int row = stmt.executeUpdate();

	        // Show how many rows were inserted
	        System.out.println(row + " record inserted.");
			
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void editItem() {
		
	}
	
	private void removeItem() {
		
	}
	
	private void displayByCategory() {
		
	}
	
	private void disconnect()
	{
		try
		{
			conn.close();
			System.out.println("Connection closed!");
			System.out.println("Goodbye!");
			keyboard.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
