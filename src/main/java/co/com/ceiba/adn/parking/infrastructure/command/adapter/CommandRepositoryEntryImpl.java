package co.com.ceiba.adn.parking.infrastructure.command.adapter;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.infrastructure.jparepository.JpaRepositoryEntry;
import co.com.ceiba.adn.parking.infrastructure.mapper.MapperEntryImpl;

@Component
public class CommandRepositoryEntryImpl implements CommandRepositoryEntry {

	private JpaRepositoryEntry jpaRepositoryEntry;

	private MapperEntryImpl mapperIngresoEntry = MapperEntryImpl.getInstance();

	public CommandRepositoryEntryImpl(JpaRepositoryEntry jpaRepositoryEntry) {
		this.jpaRepositoryEntry = jpaRepositoryEntry;
	}

	@Override
	public Entry insertEntry(Entry entry) {
		return mapperIngresoEntry.mapFromEntity(jpaRepositoryEntry.save(mapperIngresoEntry.mapToEntity(entry)));
	}
	
	@Override
	public void deleteEntry(Entry entry) {
		jpaRepositoryEntry.delete(mapperIngresoEntry.mapToEntity(entry));
	}
}
