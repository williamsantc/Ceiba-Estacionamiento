package co.com.ceiba.adn.parking.service.infrastrcuture.mapper;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.adn.parking.common.infrastucture.mapper.FactoryMapper;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

public class MapperEntryImpl implements MapperEntry {

	FactoryMapper mapper = FactoryMapper.getInstance();
	
	private static final MapperEntryImpl INSTANCE = new MapperEntryImpl(); 
	
	public static MapperEntryImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public EntityEntry mapToEntity(Entry ingreso) {
		return mapper.map(ingreso, EntityEntry.class);
	}

	@Override
	public List<Entry> mapFromEntityList(List<EntityEntry> listIngreso) {

		List<Entry> lista = new ArrayList<>();
		listIngreso.forEach(entityIngreso -> lista.add(mapper.map(entityIngreso, Entry.class)));
		return lista;
	}

	@Override
	public Entry mapFromEntity(EntityEntry entityIngreso) {
		return mapper.map(entityIngreso, Entry.class);
	}

}
