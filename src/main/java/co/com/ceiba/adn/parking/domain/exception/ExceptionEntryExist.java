package co.com.ceiba.adn.parking.domain.exception;

public class ExceptionEntryExist extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceptionEntryExist(String message) {
		super(message);
	}
}
