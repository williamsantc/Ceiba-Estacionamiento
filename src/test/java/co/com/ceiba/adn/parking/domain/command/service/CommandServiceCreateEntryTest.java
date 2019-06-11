package co.com.ceiba.adn.parking.domain.command.service;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.parking.TestBase;
import co.com.ceiba.adn.parking.service.application.exception.ExeptionEntryNotAllowed;
import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.command.serve.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;

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
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExeptionEntryNotAllowed.class,
				messageLicencePlateNotAllowed);
	}

	@Test
	public void validateInsertEntryWithCarLimitReached() {

		// Arrange
		String messageVehicleLimitReached = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldVehicleType = "CAR";
		int limitCarCount = 20;

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate("DFR345").withVehicleType(fieldVehicleType);
		Entry entry = entryTestDataBuilder.build();

		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(limitCarCount);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);

		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExeptionEntryNotAllowed.class,
				messageVehicleLimitReached);

	}

	@Test
	public void validateInsertEntryWithMotorcycleLimitReached() {

		// Arrange
		String messageVehicleLimitReached = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldVehicleType = "MOTORCYCLE";
		int limitMotorcycleCount = 10;

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate("DFR345").withVehicleType(fieldVehicleType).withEngineDisplacement("100");
		Entry entry = entryTestDataBuilder.build();

		QueryPortEntry queryPortEntry = Mockito.mock(QueryPortEntry.class);
		Mockito.when(queryPortEntry.countByVehicleType(Mockito.anyString())).thenReturn(limitMotorcycleCount);

		CommandPortEntry commandPortEntry = Mockito.mock(CommandPortEntry.class);
		Mockito.when(commandPortEntry.insertEntry(entry)).thenReturn(entry);

		CommandServiceCreateEntry commandServiceCreateEntry = new CommandServiceCreateEntry(queryPortEntry,
				commandPortEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry), ExeptionEntryNotAllowed.class,
				messageVehicleLimitReached);

	}

}
