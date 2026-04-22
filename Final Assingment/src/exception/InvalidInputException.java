package exception;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = -6446673182613498135L;
	
	public InvalidInputException() {
		super();
	}
	public InvalidInputException(String message) {
		super(message);
	}
}
