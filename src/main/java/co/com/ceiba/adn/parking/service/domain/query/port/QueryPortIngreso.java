package co.com.ceiba.adn.parking.service.domain.query.port;

import java.util.List;

import co.com.ceiba.adn.parking.service.domain.model.Ingreso;

public interface QueryPortIngreso {
	
	List<Ingreso> findAll();
	
	int countByTipoVehiculo(String tipoVehiculo);
}
