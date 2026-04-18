package problemdomain;

public abstract class Item {
	private String title;
	private long id;
	private String author;
	private String genre;
	private String publisher;
	
	public Item(String title, long id, String author, String genre, String publisher) {
		super();
		this.title = title;
		this.id = id;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}
