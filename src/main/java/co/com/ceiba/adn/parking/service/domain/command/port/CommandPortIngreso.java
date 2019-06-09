package co.com.ceiba.adn.parking.service.domain.command.port;

import co.com.ceiba.adn.parking.service.domain.model.Ingreso;

public interface CommandPortIngreso {

	Ingreso insertIngreso(Ingreso ingreso);
}
