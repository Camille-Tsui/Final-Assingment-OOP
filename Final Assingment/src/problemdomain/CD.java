package problemdomain;

public class CD extends Item {
	private boolean isDamaged;

	public CD(int id, String title, String author, String genre, String publisher, boolean isDamaged) {
		super(id, title, author, genre, publisher);
		this.isDamaged = isDamaged;
	}

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	@Override
	public String toString() {
		return "CD " + super.toString() + " | Damaged: " + isDamaged;
	}

}