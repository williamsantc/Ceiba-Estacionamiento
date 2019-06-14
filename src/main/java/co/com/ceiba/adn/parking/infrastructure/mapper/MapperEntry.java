package co.com.ceiba.adn.parking.infrastructure.mapper;

import java.util.List;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryDto;
import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;

public interface MapperEntry {
	
	EntityEntry mapToEntity (Entry entry);
	
	Entry mapFromEntity (EntityEntry entityEntry);
	
	List<EntryDto> mapFromEntityList (List<EntityEntry> listEntry);
}
