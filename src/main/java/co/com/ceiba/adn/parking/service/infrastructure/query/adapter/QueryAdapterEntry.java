package co.com.ceiba.adn.parking.service.infrastructure.query.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;
import co.com.ceiba.adn.parking.service.infrastrcuture.mapper.MapperEntryImlp;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryEntry;

@Component
public class QueryAdapterEntry implements QueryPortEntry {
	
	@Autowired
	private RepositoryEntry repositoryEntry;
	
	@Autowired
	private MapperEntryImlp mapperEntryImlp;

	@Override
	public List<Entry> findAll() {
		return this.mapperEntryImlp.mapFromEntityList(repositoryEntry.findAll());
	}

	@Override
	public int countByVehicleType(String vehicleType) {
		return this.repositoryEntry.countByVehicleType(vehicleType);
	}

}
