package manager;

import java.sql.*;
import java.util.ArrayList;
import problemdomain.Item;
import problemdomain.Rental;
import problemdomain.User;

public class RentalManager {
	private Connection conn;
	private Statement stmt;

	private ArrayList<Rental> rentals = new ArrayList<>();

	public RentalManager() {
		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.createStatement();
			loadRentals();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	private boolean userExists(int userId) {
		String sql = "SELECT * FROM users WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private boolean itemExists(int itemId) {
		String sql = "SELECT * FROM items WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void loadRentals() {
		String sqlStatement = "SELECT * FROM rental;";
		rentals.clear();

		try {
			ResultSet rs = stmt.executeQuery(sqlStatement);

			while (rs.next()) {
				User user = new User(
						rs.getInt("user_id"),
						"",
						"",
						"",
						"");

				Item item = new Item(
						rs.getInt("item_id"),
						"",
						"",
						"",
						"");

				Rental rental = new Rental(
						rs.getInt("id"),
						user,
						item,
						rs.getString("startDate"),
						rs.getString("returnDate"),
						rs.getString("date_returned"));

				rentals.add(rental);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addRental(int id, int userId, int itemId, String startDate, String returnDate, String dateReturned) {
		if (!userExists(userId)) {
			System.out.println("User ID does not exist.");
			return;
		}

		if (!itemExists(itemId)) {
			System.out.println("Item ID does not exist.");
			return;
		}

		String sqlStmt = "INSERT INTO rental (id, user_id, item_id, startDate, returnDate, date_returned) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.setInt(2, userId);
			stmt.setInt(3, itemId);
			stmt.setString(4, startDate);
			stmt.setString(5, returnDate);
			stmt.setString(6, dateReturned);
			stmt.executeUpdate();
			loadRentals();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeRental(int id) {
		String sqlStmt = "DELETE FROM rental WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStmt);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			loadRentals();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void displayRentals() {
		for (Rental rental : rentals) {
			System.out.println(rental);
		}
	}

	public void disconnect() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Connection closed!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}