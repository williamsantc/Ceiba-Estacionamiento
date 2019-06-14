package co.com.ceiba.adn.parking.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryDto;
import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;

public class MapperEntryImpl implements MapperEntry {

	private static final MapperEntryImpl INSTANCE = new MapperEntryImpl();

	public static MapperEntryImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public EntityEntry mapToEntity(Entry entry) {
		if (entry == null) {
			return null;
		}
		return new EntityEntry(entry.getId(), entry.getLicencePlate(), entry.getVehicleType(),
				entry.getEngineDisplacement(), entry.getEntryTime());
	}

	@Override
	public List<EntryDto> mapFromEntityList(List<EntityEntry> listEntry) {

		List<EntryDto> lista = new ArrayList<>();
		listEntry.forEach(entityEntry -> lista.add(
				new EntryDto(entityEntry.getLicencePlate(), entityEntry.getVehicleType(), entityEntry.getEntryTime())));
		return lista;
	}

	@Override
	public Entry mapFromEntity(EntityEntry entityEntry) {
		if (entityEntry == null) {
			return null;
		}
		return new Entry(entityEntry.getId(), entityEntry.getLicencePlate(), entityEntry.getVehicleType(),
				entityEntry.getEngineDisplacement(), entityEntry.getEntryTime());
	}

}
