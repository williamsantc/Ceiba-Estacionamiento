package co.com.ceiba.adn.parking.domain.exception;

public class ExceptionWrongVehicleType extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceptionWrongVehicleType(String message) {
		super(message);
	}

}
