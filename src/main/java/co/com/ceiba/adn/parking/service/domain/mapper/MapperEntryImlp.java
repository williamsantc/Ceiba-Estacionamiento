package co.com.ceiba.adn.parking.service.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

@Component
public class MapperEntryImlp implements MapperEntry {

	@Override
	public EntityEntry mapToEntity(Entry entry) {
		return new EntityEntry(entry.getId(), entry.getLicencePlate(), entry.getVehicleType(),
				entry.getEngineDisplacement(), entry.getEntryTime());
	}

	@Override
	public List<Entry> mapFromEntityList(List<EntityEntry> listIngreso) {

		List<Entry> lista = new ArrayList<>();
		listIngreso.forEach(entityEntry -> lista.add(new Entry(entityEntry.getLicencePlate(),
				entityEntry.getVehicleType(), entityEntry.getEngineDisplacement(), entityEntry.getEntryTime())));
		return lista;
	}

	@Override
	public Entry mapFromEntity(EntityEntry entityEntry) {
		return new Entry(entityEntry.getLicencePlate(), entityEntry.getVehicleType(),
				entityEntry.getEngineDisplacement(), entityEntry.getEntryTime());
	}

}
