package co.com.ceiba.adn.parking.common.domain.exepcion;

public class ExcepcionInvalidValue extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionInvalidValue(String message) {
        super(message);
    }
}
