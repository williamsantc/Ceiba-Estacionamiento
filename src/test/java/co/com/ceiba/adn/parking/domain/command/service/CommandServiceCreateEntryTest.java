package co.com.ceiba.adn.parking.domain.command.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.adn.TestBase;
import co.com.ceiba.adn.parking.application.exception.ExceptionEntryNotAllowed;
import co.com.ceiba.adn.parking.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.port.QueryPortEntry;

public class CommandServiceCreateEntryTest {

	@Test
	public void validateInsertEntryWithLicencePlateValueA() {
		// Arrange
		String messageLicencePlateNotAllowed = "Ingreso no permitido, el tipo de placa indicado solo tiene permitido el ingreso los días domingo y lunes.";

		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY); // Date greater than MONDAY

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate("ASD123").withEntryTime(date);
		Entry entry = entryTestDataBuilder.build();

		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(4);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);

		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExceptionEntryNotAllowed.class,
				messageLicencePlateNotAllowed);
	}

	@Test
	public void validateInsertEntryWithCarLimitReached() {

		// Arrange
		String messageVehicleLimitReached = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldVehicleType = "CAR";
		String fieldLicencePlate = "DFR345";
		int limitCarCount = 20;

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate(fieldLicencePlate).withVehicleType(fieldVehicleType);
		Entry entry = entryTestDataBuilder.build();

		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(limitCarCount);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);

		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExceptionEntryNotAllowed.class,
				messageVehicleLimitReached);

	}

	@Test
	public void validateInsertEntryWithMotorcycleLimitReached() {

		// Arrange
		String messageVehicleLimitReached = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldVehicleType = "MOTORCYCLE";
		String fildLicencePlate = "HFR345";
		int limitMotorcycleCount = 10;

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate(fildLicencePlate).withVehicleType(fieldVehicleType).withEngineDisplacement("100");
		Entry entry = entryTestDataBuilder.build();

		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(limitMotorcycleCount);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);

		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExceptionEntryNotAllowed.class,
				messageVehicleLimitReached);

	}


	@Test
	public void doCorrentInsert() {
		
		// Arrange
		int limitMotorcycleCount = 10;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate("DFR345");
		Entry entry = entryTestDataBuilder.build();
		entry.setId(1L);
		
	
		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(limitMotorcycleCount);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);
		
		
		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);
		
		// Act
		Long idCreated = commandServiceCreateEntry.exec(entry);
		
		// Assert
		assertEquals(idCreated, entry.getId());
		
	}

}
