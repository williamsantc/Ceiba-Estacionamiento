package co.com.ceiba.adn.parking.infrastructure.query.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryDto;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;
import co.com.ceiba.adn.parking.infrastrcuture.mapper.MapperEntryImpl;
import co.com.ceiba.adn.parking.infrastructure.jparepository.JpaRepositoryEntry;

@Component
public class QueryRepositoryEntryImpl implements QueryRepositoryEntry {
	
	private final JpaRepositoryEntry repositoryEntry;
	
	private final MapperEntryImpl mapperIngresoEntry = MapperEntryImpl.getInstance();
	
	public QueryRepositoryEntryImpl(JpaRepositoryEntry repositoryEntry) {
		this.repositoryEntry = repositoryEntry;
	}

	@Override
	public List<EntryDto> findAll() {
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
