package co.com.ceiba.adn.parking.infrastrcuture.mapper;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.adn.common.infrastucture.mapper.FactoryMapper;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryCore;
import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;
import co.com.ceiba.adn.parking.infrastructure.exception.ExceptionEntryNotFound;

public class MapperEntryImpl implements MapperEntry {

	private static final String MESSAGE_ENTRY_NOT_FOUND = "No se encuentró ningún vehiculo en el parqueadero con la placa proporcionada";

	FactoryMapper mapper = FactoryMapper.getInstance();

	private static final MapperEntryImpl INSTANCE = new MapperEntryImpl();

	public static MapperEntryImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public EntityEntry mapToEntity(Entry entry) {
		return mapper.map(entry, EntityEntry.class);
	}

	@Override
	public List<EntryCore> mapFromEntityList(List<EntityEntry> listEntry) {

		List<EntryCore> lista = new ArrayList<>();
		listEntry.forEach(entityEntry -> lista.add(new EntryCore(entityEntry.getLicencePlate(),
				entityEntry.getVehicleType(), entityEntry.getEntryTime())));
		return lista;
	}

	@Override
	public Entry mapFromEntity(EntityEntry entityEntry) {
		if (entityEntry == null) {
			throw new ExceptionEntryNotFound(MESSAGE_ENTRY_NOT_FOUND);
		}
		return mapper.map(entityEntry, Entry.class);
	}

}
