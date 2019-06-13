package co.com.ceiba.adn.parking.domain.model;

import org.junit.Test;

import co.com.ceiba.adn.TestBase;
import co.com.ceiba.adn.common.domain.exception.ExceptionRequiredValue;
import co.com.ceiba.adn.parking.domain.command.testdatabuilder.EntryTestDataBuilder;
import co.com.ceiba.adn.parking.domain.exception.ExceptionWrongVehicleType;

public class ModelIngresoTest {

	@Test
	public void validateRequiredVehicleType() {

		// Arrange
		String messageVehicleTypeRequired = "Campos incompletos, el campo tipo vehiculo es querido.";
		EntryTestDataBuilder ingresoTestDataBuilder = new EntryTestDataBuilder();
		ingresoTestDataBuilder.withVehicleType(null);

		// Act - Assert
		TestBase.assertThrows(ingresoTestDataBuilder::build, ExceptionRequiredValue.class, messageVehicleTypeRequired);
	}

	@Test
	public void validateRequiredLicencePlace() {

		// Arrange
		String messageLicencePlateRequired = "Campos incompletos, el campo placa es querido.";
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate(null);

		// Act - Assert
		TestBase.assertThrows(entryTestDataBuilder::build, ExceptionRequiredValue.class, messageLicencePlateRequired);
	}

	@Test
	public void validateRequiredEngineDisplacementOnVehicleTypeHasValueMotorcycle() {

		// Arrange
		String messageEngineDisplacementRequired = "Campos incompletos, el campo cilindraje es querido.";
		String fieldVehicleType = "MOTORCYCLE";
		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withVehicleType(fieldVehicleType);

		// Act - Assert
		TestBase.assertThrows(entryTestDataBuilder::build, ExceptionRequiredValue.class,
				messageEngineDisplacementRequired);
	}

	@Test
	public void validateInsertEntryWithWrongVehicleType() {
		String messageWrongVehicleType = "Error, el campo tipo vehiculo posee un dato no válido.";
		String fieldVehicleType = "SOME";
		String fieldLicencePlate = "DFR345";

		EntryTestDataBuilder entryTestDataBuilder = new EntryTestDataBuilder();
		entryTestDataBuilder.withLicencePlate(fieldLicencePlate).withVehicleType(fieldVehicleType);

		// Act - Assert
		TestBase.assertThrows(entryTestDataBuilder::build, ExceptionWrongVehicleType.class, messageWrongVehicleType);
	}
}
