package co.com.ceiba.adn.parking.domain.query.port;

import java.util.List;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryCore;

public interface QueryPortEntry {
	
	List<EntryCore> findAll();
	
	int countByVehicleType(String tipoVehiculo);
	
	Entry findByLicencePlate(String licencePlate);
}
