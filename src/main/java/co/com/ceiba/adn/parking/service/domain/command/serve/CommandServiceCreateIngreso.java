package co.com.ceiba.adn.parking.service.domain.command.serve;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.application.exception.ExeptionIngresoNoPermitido;
import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortIngreso;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortIngreso;

@Component
public class CommandServiceCreateIngreso {

	private static final String FIELD_TIPOVEHICULO_VALUE_CARRO = "CARRO";
	private static final String FIELD_TIPOVEHICULO_VALUE_MOTO = "MOTO";
	private static final int LIMIT_COUNT_CARROS = 20;
	private static final int LIMIT_COUNT_MOTOS = 10;
	private static final String PLACA_START_WITH = "A";
	private static final String MESSAGE_LIMITE_DE_VEHICULOS_ALCANZADO = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
	private static final String MESSAGE_PLACA_NO_PERMETIDA = "Ingreso no permitido, el tipo de placa indicado solo tiene permitido el ingreso los días domingo y lunes.";

	@Autowired
	private CommandPortIngreso commandPortIngreso;

	@Autowired
	private QueryPortIngreso queryPortIngreso;
	
	// For testing purposes
	public CommandServiceCreateIngreso (QueryPortIngreso queryPortIngreso, CommandPortIngreso commandPortIngreso) {
		this.queryPortIngreso = queryPortIngreso;
		this.commandPortIngreso = commandPortIngreso;
	}

	public Long exec(Ingreso ingreso) {
		this.validateDisponibilidadIngresoByPlaca(ingreso.getPlaca(), ingreso.getRegistroEntrada());
		this.validateDisponibilidadIngresoByTipoVehiculo(ingreso.getTipoVehiculo());
		return this.commandPortIngreso.insertIngreso(ingreso).getId();
	}

	private void validateDisponibilidadIngresoByTipoVehiculo(String tipoVehiculo) {
		int countTipoVehiculos = this.queryPortIngreso.countByTipoVehiculo(tipoVehiculo);
		if (tipoVehiculo.equalsIgnoreCase(FIELD_TIPOVEHICULO_VALUE_CARRO) && countTipoVehiculos >= LIMIT_COUNT_CARROS
				|| tipoVehiculo.equalsIgnoreCase(FIELD_TIPOVEHICULO_VALUE_MOTO) && countTipoVehiculos >= LIMIT_COUNT_MOTOS) {
			throw new ExeptionIngresoNoPermitido(MESSAGE_LIMITE_DE_VEHICULOS_ALCANZADO);
		}
	}

	private void validateDisponibilidadIngresoByPlaca(String placa, Calendar date) {
		int day = date.get(Calendar.DAY_OF_WEEK);
		if (placa.startsWith(PLACA_START_WITH) && day > Calendar.MONDAY) {
			throw new ExeptionIngresoNoPermitido(MESSAGE_PLACA_NO_PERMETIDA);
		}
	}
}
