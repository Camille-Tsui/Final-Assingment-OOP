package problemdomain;

public class Book extends Item{
	private boolean isDamaged;

	public Book(String title, long id, String author, String genre, String publisher, boolean isDamaged) {
		super(title, id, author, genre, publisher);
		this.isDamaged = isDamaged;
	}
}
