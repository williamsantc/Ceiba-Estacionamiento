package co.com.ceiba.adn.parking.service.domain.model;

import java.util.Calendar;

import static co.com.ceiba.adn.parking.common.domain.ArgValidator.validateRequired;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ingreso {

	// Constants

	private static final String MESSAGE_PLACA_REQUIRED = "Campos incompletos, el campo placa es querido.";
	private static final String MESSAGE_TIPO_VEHICULO_REQUIRED = "Campos incompletos, el campo tipo vehiculo es querido.";
	private static final String MESSAGE_CILINDRAJE_REQUIRED = "Campos incompletos, el campo cilindraje es querido.";
	private static final String FIELD_TIPOVEHICULO_VALUE_MOTO = "MOTO";

	private Long id;

	private String placa;

	private String tipoVehiculo;

	private String cilindraje;

	private Calendar registroEntrada;

	public Ingreso(Long id, String placa, String tipoVehiculo, String cilindaje, Calendar registroEntrada) {
		validateRequired(placa, MESSAGE_PLACA_REQUIRED);
		validateRequired(tipoVehiculo, MESSAGE_TIPO_VEHICULO_REQUIRED);
		if (tipoVehiculo != null && tipoVehiculo.equalsIgnoreCase(FIELD_TIPOVEHICULO_VALUE_MOTO)) {
			validateRequired(cilindaje, MESSAGE_CILINDRAJE_REQUIRED);
		}
		this.id = id;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindaje;
		this.registroEntrada = registroEntrada;
	}

}
