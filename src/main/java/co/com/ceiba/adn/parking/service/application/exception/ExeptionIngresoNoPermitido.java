package co.com.ceiba.adn.parking.service.application.exception;

public class ExeptionIngresoNoPermitido extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExeptionIngresoNoPermitido(String message) {
		super(message);
	}

}
