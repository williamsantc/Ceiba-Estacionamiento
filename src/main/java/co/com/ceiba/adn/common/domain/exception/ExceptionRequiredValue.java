package co.com.ceiba.adn.common.domain.exception;

public class ExceptionRequiredValue extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExceptionRequiredValue(String message) {
        super(message);
    }
}
