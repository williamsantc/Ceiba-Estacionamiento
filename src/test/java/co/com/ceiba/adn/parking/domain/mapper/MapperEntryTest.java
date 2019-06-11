package co.com.ceiba.adn.parking.domain.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.infrastructure.command.testdatabuilder.EntityEntryTestDataBuilder;
import co.com.ceiba.adn.parking.service.domain.mapper.MapperEntryImlp;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

public class MapperEntryTest {

	private static final MapperEntryImlp mapperEntry = new MapperEntryImlp();

	@Test
	public void mapToEntity() {

		// Arrange
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Entry entry = entryTestDataBuilder.build();

		// Act
		EntityEntry entityEntry = mapperEntry.mapToEntity(entry);

		// Assert
		assertNotNull(entityEntry);
	}

	@Test
	public void mapFromEntity() {

		// Arrange
		EntityEntryTestDataBuilder entityEntryTestDataBuilder = new EntityEntryTestDataBuilder();
		EntityEntry entityEntry = entityEntryTestDataBuilder.build();

		// Act
		Entry entry = mapperEntry.mapFromEntity(entityEntry);

		// Assert
		assertNotNull(entry);
	}

	@Test
	public void mapFromEntityList() {

		// Arrange
		EntityEntryTestDataBuilder entityEntryTestDataBuilder = new EntityEntryTestDataBuilder();
		entityEntryTestDataBuilder.withLicencePlate("ART123");
		EntityEntry entityEntry = entityEntryTestDataBuilder.build();
		ArrayList<EntityEntry> listEntityEntry = new ArrayList<>();
		listEntityEntry.add(entityEntry);

		// Act
		ArrayList<Entry> listEntry = new ArrayList<>(mapperEntry.mapFromEntityList(listEntityEntry));

		// Assert
		assertNotNull(listEntry);

	}
}
