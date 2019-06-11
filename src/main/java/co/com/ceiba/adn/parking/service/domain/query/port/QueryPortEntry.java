package co.com.ceiba.adn.parking.service.domain.query.port;

import java.util.List;

import co.com.ceiba.adn.parking.service.domain.model.Entry;

public interface QueryPortEntry {
	
	List<Entry> findAll();
	
	int countByVehicleType(String tipoVehiculo);
}
