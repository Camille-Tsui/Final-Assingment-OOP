package exception;

public class ItemNotFoundException extends Exception {

 static final long serialVersionUID = 6989886345176248823L;

	public ItemNotFoundException(String message) {
		super(message);
	}
}