package problemdomain;

public class EBook extends Item {
	private double fileSize;

	public EBook(int id, String title, String author, String genre, String publisher, double fileSize) {
		super(id, title, author, genre, publisher);
		this.fileSize = fileSize;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "EBook [fileSize=" + fileSize + ", " + super.toString() + "]";
	}
}