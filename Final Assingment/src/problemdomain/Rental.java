package problemdomain;

public class Rental {
	private int id;
	private int userId;
	private int itemId;
	private String startDate;
	private String dueDate;
	private String returnDate;

	public Rental(int id, int userId, int itemId, String startDate, String dueDate, String returnDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public int getUser() {
		return userId;
	}

	public int getItem() {
		return itemId;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	public void setItem(int itemId) {
		this.itemId = itemId;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Rental [" + id + "] | User: " + userId + " | Item: " + itemId + " | Start Date: " + startDate + " | Due Date: "
				+ dueDate + " | Return Date: " + returnDate + "]";
	}
}