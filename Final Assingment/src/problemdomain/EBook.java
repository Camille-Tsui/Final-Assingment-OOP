package problemdomain;

public class EBook extends Item {
	private int fileSize;

	public EBook(int id, String title, String author, String genre, String publisher, int fileSize) {
		super(id, title, author, genre, publisher);
		this.fileSize = fileSize;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "EBook " + super.toString() + " | File size: " + fileSize;
	}

}