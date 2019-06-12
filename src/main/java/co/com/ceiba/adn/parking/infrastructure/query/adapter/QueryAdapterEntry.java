package co.com.ceiba.adn.parking.infrastructure.query.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryCore;
import co.com.ceiba.adn.parking.domain.query.port.QueryPortEntry;
import co.com.ceiba.adn.parking.infrastrcuture.mapper.MapperEntryImpl;
import co.com.ceiba.adn.parking.infrastructure.repository.RepositoryEntry;

@Component
public class QueryAdapterEntry implements QueryPortEntry {
	
	private final RepositoryEntry repositoryEntry;
	
	private final MapperEntryImpl mapperIngresoEntry = MapperEntryImpl.getInstance();
	
	public QueryAdapterEntry(RepositoryEntry repositoryEntry) {
		this.repositoryEntry = repositoryEntry;
	}

	@Override
	public List<EntryCore> findAll() {
		return this.mapperIngresoEntry.mapFromEntityList(repositoryEntry.findAll());
	}

	@Override
	public int countByVehicleType(String vehicleType) {
		return this.repositoryEntry.countByVehicleType(vehicleType);
	}
	
	@Override
	public Entry findByLicencePlate(String licencePlate) {
		return this.mapperIngresoEntry.mapFromEntity(this.repositoryEntry.findByLicencePlate(licencePlate));
	}

}
