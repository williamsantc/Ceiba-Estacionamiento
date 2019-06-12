package co.com.ceiba.adn.parking.infrastrcuture.mapper;

import java.util.List;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryCore;
import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;

public interface MapperEntry {
	
	EntityEntry mapToEntity (Entry entry);
	
	Entry mapFromEntity (EntityEntry entityEntry);
	
	List<EntryCore> mapFromEntityList (List<EntityEntry> listEntry);
}
