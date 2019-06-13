package co.com.ceiba.adn.parking.domain.query.repository;

import java.util.List;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryDto;

public interface QueryRepositoryEntry {
	
	List<EntryDto> findAll();
	
	int countByVehicleType(String tipoVehiculo);
	
	Entry findByLicencePlate(String licencePlate);
}
