package co.com.ceiba.adn.parking.service.application.command.factory;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.application.command.CommandIngreso;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;

@Component
public class FactoryIngreso {

	private static final FactoryIngreso INSTANCE = new FactoryIngreso();

	public static FactoryIngreso getInstance() {
		return INSTANCE;
	}

	public Ingreso create(CommandIngreso commandIngreso) {
		return new Ingreso(commandIngreso.getId(), commandIngreso.getPlaca(), commandIngreso.getTipoVehiculo(),
				commandIngreso.getCilindraje(), Calendar.getInstance());
	}
}
