package co.com.ceiba.adn.parking.common.domain.exepcion;

public class ExcepcionEmpty extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionEmpty(String message) {
        super(message);
    }
}
