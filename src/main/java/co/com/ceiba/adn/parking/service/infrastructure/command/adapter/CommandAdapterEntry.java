package co.com.ceiba.adn.parking.service.infrastructure.command.adapter;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastrcuture.mapper.MapperEntryImpl;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryEntry;

@Component
public class CommandAdapterEntry implements CommandPortEntry {

	private RepositoryEntry repositoryEntry;

	private MapperEntryImpl mapperIngresoEntry = MapperEntryImpl.getInstance();

	public CommandAdapterEntry(RepositoryEntry repositoryEntry) {
		this.repositoryEntry = repositoryEntry;
	}

	@Override
	public Entry insertEntry(Entry entry) {
		return mapperIngresoEntry.mapFromEntity(repositoryEntry.save(mapperIngresoEntry.mapToEntity(entry)));
	}
}
