package co.com.ceiba.adn.parking.service.infrastructure.command.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastrcuture.mapper.MapperEntryImlp;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryEntry;

@Component
public class CommandAdapterEntry implements CommandPortEntry {

	@Autowired
	private RepositoryEntry repositoryEntry;
	
	@Autowired
	private MapperEntryImlp mapperIngresoEntry;

	@Override
	public Entry insertEntry(Entry entry) {
		return mapperIngresoEntry.mapFromEntity(repositoryEntry.save(mapperIngresoEntry.mapToEntity(entry)));
	}
}
