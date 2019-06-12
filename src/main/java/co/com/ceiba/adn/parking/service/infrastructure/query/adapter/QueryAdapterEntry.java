package co.com.ceiba.adn.parking.service.infrastructure.query.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;
import co.com.ceiba.adn.parking.service.infrastrcuture.mapper.MapperEntryImpl;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryEntry;

@Component
public class QueryAdapterEntry implements QueryPortEntry {
	
	private final RepositoryEntry repositoryEntry;
	
	private final MapperEntryImpl mapperIngresoEntry = MapperEntryImpl.getInstance();
	
	public QueryAdapterEntry(RepositoryEntry repositoryEntry) {
		this.repositoryEntry = repositoryEntry;
	}

	@Override
	public List<Entry> findAll() {
		return this.mapperIngresoEntry.mapFromEntityList(repositoryEntry.findAll());
	}

	@Override
	public int countByVehicleType(String vehicleType) {
		return this.repositoryEntry.countByVehicleType(vehicleType);
	}

}
