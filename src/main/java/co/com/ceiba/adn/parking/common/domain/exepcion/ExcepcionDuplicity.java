package co.com.ceiba.adn.parking.common.domain.exepcion;

public class ExcepcionDuplicity extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionDuplicity(String mensaje) {
        super(mensaje);
    }
}
