package co.com.ceiba.adn.parking.common.domain.exepcion;

public class ExcepcionRequiredValue extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionRequiredValue(String message) {
        super(message);
    }
}
