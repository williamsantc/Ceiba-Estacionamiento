package co.com.ceiba.adn.parking.service.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.common.domain.mapper.FactoryMapper;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

@Component
public class MapperEntryImlp implements MapperEntry {
	
	FactoryMapper mapper = FactoryMapper.getInstance();
	
	@Override
	public EntityEntry mapToEntity(Entry ingreso) {
		return mapper.map(ingreso, EntityEntry.class);
	}

	@Override
	public List<Entry> mapFromEntityList(List<EntityEntry> listIngreso) {
		
		List<Entry> lista = new ArrayList<>();
		listIngreso.forEach(entityIngreso -> 
			lista.add(mapper.map(entityIngreso, Entry.class))
		);
		return lista;
	}
	
	@Override
	public Entry mapFromEntity(EntityEntry entityIngreso) {
		return mapper.map(entityIngreso, Entry.class);
	}

}
