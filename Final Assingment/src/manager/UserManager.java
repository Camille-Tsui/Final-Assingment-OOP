package manager;

import java.sql.*;
import java.util.ArrayList;
import problemdomain.User;

public class UserManager {

	private Connection conn;
	private Statement stmt;

	private ArrayList<User> users = new ArrayList<>();

	public UserManager() {
		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.createStatement();
			loadUsers();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadUsers() {
		String sqlStatement = "SELECT * FROM users;";
		users.clear();

		try {
			ResultSet rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("dateOfBirth"),
						rs.getString("phoneNumber"));
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addUser(int id, String firstName, String lastName, String dateOfBirth, String phoneNumber) {
		String sqlStmt = "INSERT INTO users (id, firstName, lastName, dateOfBirth, phoneNumber) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, dateOfBirth);
			stmt.setString(5, phoneNumber);
			stmt.executeUpdate();
			loadUsers();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeUser(int id) {
		String sqlStmt = "DELETE FROM users WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			loadUsers();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void displayUsers() {
		for (User user : users) {
			System.out.println(user);
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
			System.out.println(e.getMessage());
		}
	}
}