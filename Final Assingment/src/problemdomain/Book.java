package problemdomain;

public class Book extends Item {
	private boolean isDamaged;

	public Book(int id, String title, String author, String genre, String publisher, boolean isDamaged) {
		super(id, title, author, genre, publisher);
		this.isDamaged = isDamaged;
	}
}