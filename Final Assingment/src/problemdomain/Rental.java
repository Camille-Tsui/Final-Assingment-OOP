package problemdomain;

public class Rental {
	private int id;
	private User user;
	private Item item;
	private String startDate;
	private String dueDate;
	private String returnDate;

	public Rental(int id, User user, Item item, String startDate, String dueDate, String returnDate) {
		super();
		this.id = id;
		this.user = user;
		this.item = item;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Item getItem() {
		return item;
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

	public void setUser(User user) {
		this.user = user;
	}

	public void setItem(Item item) {
		this.item = item;
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
		return "Rental [" + id + "] | User: " + user + " | Item: " + item + " | Start Date: " + startDate + " | Due Date: "
				+ dueDate + " | Return Date: " + returnDate + "]";
	}
}