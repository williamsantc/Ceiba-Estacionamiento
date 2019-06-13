package co.com.ceiba.adn.parking.infrastructure.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import co.com.ceiba.adn.TestBase;
import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.model.EntryDto;
import co.com.ceiba.adn.parking.infrastrcuture.mapper.MapperEntryImpl;
import co.com.ceiba.adn.parking.infrastructure.command.testdatabuilder.EntityEntryTestDataBuilder;
import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;
import co.com.ceiba.adn.parking.infrastructure.exception.ExceptionEntryNotFound;

public class MapperEntryTest {

	private static final MapperEntryImpl mapperEntry = MapperEntryImpl.getInstance();

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
	public void mapFromEntityFail() {

		// Arrange
		EntityEntry entityEntry = null;

		// Act - Assert
		TestBase.assertThrows(() -> mapperEntry.mapFromEntity(entityEntry), ExceptionEntryNotFound.class);
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
		ArrayList<EntryDto> listEntry = new ArrayList<>(mapperEntry.mapFromEntityList(listEntityEntry));

		// Assert
		assertNotNull(listEntry);

	}
}
