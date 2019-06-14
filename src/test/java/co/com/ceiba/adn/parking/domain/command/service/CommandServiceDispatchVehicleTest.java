package co.com.ceiba.adn.parking.domain.command.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.adn.TestBase;
import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotFound;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

public class CommandServiceDispatchVehicleTest {

	private static final Double HOUR_PRICE_CAR = 1000D;
	private static final Double DAY_PRICE_CAR = 8000D;

	private static final Double HOUR_PRICE_MOTORCYCLE = 500D;
	private static final Double DAY_PRICE_MOTORCYCLE = 4000D;
	private static final int HIGH_ENGINE_DISPLACEMENT = 500;
	private static final Double SURCHARGE_HIGH_ENGINE_DISPLACEMENT = 2000D;

	private static final int MAX_HOURS_OF_DAY = 9;

	private static final int DAY_AS_HOURS = 24;

	private static final String MOTORCYCLE = "MOTORCYCLE";

	@Test
	public void testPaymentVehicleNotFound() {

		// Arrange
		String messageEntryFound = "No se encontró ningún vehiculo en el parqueadero con la placa proporcionada";
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();

		Entry entry = entryTestDataBuilder.build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(null);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateEntry.exec(entry.getLicencePlate()),
				ExceptionEntryNotFound.class, messageEntryFound);

	}

	@Test
	public void testPaymentCarWithTimeLessThanHour() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_CAR * 1;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentCarWithTimeLessThanTwoHours() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_CAR * 2;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - 1);
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentCarWithTimeLessThanNineHours() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_CAR * MAX_HOURS_OF_DAY;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - (MAX_HOURS_OF_DAY - 1));
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentCarWithTimeMoreThanNineHours() {

		// Arrange
		Double expectedPrice = DAY_PRICE_CAR * 1;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - MAX_HOURS_OF_DAY);
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentCarWithTimeMoreThanOneDayAndTwoHours() {

		// Arrange
		Double expectedPrice = DAY_PRICE_CAR * 1 + HOUR_PRICE_CAR * 3;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - DAY_AS_HOURS - 2);
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentMotorcycleWithTimeLessThanHour() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_MOTORCYCLE * 1;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);

		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement("200").build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentMotorcycleWithTimeLessThanTwoHours() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_MOTORCYCLE * 2;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - 1);

		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement("200").build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}

	@Test
	public void testPaymentMotorcycleWithTimeLessThanNineHours() {

		// Arrange
		Double expectedPrice = HOUR_PRICE_MOTORCYCLE * MAX_HOURS_OF_DAY;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - (MAX_HOURS_OF_DAY - 1));

		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement("200").build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}
	
	@Test
	public void testPaymentMotorcycleWithTimeMoreThanNineHours() {

		// Arrange
		Double expectedPrice = DAY_PRICE_MOTORCYCLE * 1;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - MAX_HOURS_OF_DAY);
		
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement("200").build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}
	
	@Test
	public void testPaymentMotorcycleWithTimeMoreThanOneDayAndTwoHours() {

		// Arrange
		Double expectedPrice = DAY_PRICE_MOTORCYCLE * 1 + HOUR_PRICE_MOTORCYCLE * 3;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - DAY_AS_HOURS - 2);
		
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement("200").build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}
	
	@Test
	public void testPaymentMotorcycleWithTimeMoreThanOneDayAndTwoHoursAndEngineDisplacementGreaterThanFiveHundred() {

		// Arrange
		Double expectedPrice = DAY_PRICE_MOTORCYCLE * 1 + HOUR_PRICE_MOTORCYCLE * 3 + SURCHARGE_HIGH_ENGINE_DISPLACEMENT;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - DAY_AS_HOURS - 2);
		
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement(String.valueOf(HIGH_ENGINE_DISPLACEMENT + 1)).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}
	
	@Test
	public void testPaymentMotorcycleWithTimeMoreThanOneDayAndTwoHoursAndEngineDisplacementEqualToFiveHundred() {

		// Arrange
		Double expectedPrice = DAY_PRICE_MOTORCYCLE * 1 + HOUR_PRICE_MOTORCYCLE * 3;
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		Calendar alteredTime = Calendar.getInstance();
		alteredTime.set(Calendar.MINUTE, alteredTime.get(Calendar.MINUTE) - 1);
		alteredTime.set(Calendar.HOUR_OF_DAY, alteredTime.get(Calendar.HOUR_OF_DAY) - DAY_AS_HOURS - 2);
		
		Entry entry = entryTestDataBuilder.withEntryTime(alteredTime).withVehicleType(MOTORCYCLE)
				.withEngineDisplacement(String.valueOf(HIGH_ENGINE_DISPLACEMENT)).build();

		QueryRepositoryEntry queryRepositoryEntry = Mockito.mock(QueryRepositoryEntry.class);
		Mockito.when(queryRepositoryEntry.findByLicencePlate(Mockito.anyString())).thenReturn(entry);

		CommandRepositoryEntry commandRepositoryEntry = Mockito.mock(CommandRepositoryEntry.class);

		CommandServiceDispatchVehicle commandServiceCreateEntry = new CommandServiceDispatchVehicle(
				queryRepositoryEntry, commandRepositoryEntry);

		// Act
		Double price = commandServiceCreateEntry.exec(entry.getLicencePlate());

		// Assert
		assertEquals(expectedPrice, price, 0);

	}
}
