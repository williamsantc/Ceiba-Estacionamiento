package co.com.ceiba.adn.parking.service.infrastrcuture.mapper;

import java.util.List;

import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

public interface MapperEntry {
	
	EntityEntry mapToEntity (Entry entry);
	
	Entry mapFromEntity (EntityEntry entityEntry);
	
	List<Entry> mapFromEntityList (List<EntityEntry> listEntry);
}
