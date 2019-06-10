package co.com.ceiba.adn.parking.domain.model;

import org.junit.Test;

import co.com.ceiba.adn.parking.common.domain.exception.ExceptionRequiredValue;
import co.com.ceiba.adn.parking.domain.command.testdatabuilder.IngresoTestDataBuilder;
import co.com.ceiba.adn.parking.parking.TestBase;

public class ModelIngresoTest {

	@Test
	public void validateRequiredTipoVehiculo() {
		
		// Arrange
		String messageTipoVehiculoRequired = "Campos incompletos, el campo tipo vehiculo es querido.";
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withTipoVehiculo(null);
		
		// Act - Assert
		TestBase.assertThrows(ingresoTestDataBuilder::build, ExceptionRequiredValue.class,
				messageTipoVehiculoRequired);
	}
	
	@Test
	public void validateRequiredPlaca() {
		
		// Arrange
		String messageTipoVehiculoRequired = "Campos incompletos, el campo placa es querido.";
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withPlaca(null);
		
		// Act - Assert
		TestBase.assertThrows(ingresoTestDataBuilder::build, ExceptionRequiredValue.class,
				messageTipoVehiculoRequired);
	}
	
	@Test
	public void validateRequiredCilindrajeOnTipoVehiculoHasValueMoto() {
		
		// Arrange
		String messageTipoVehiculoRequired = "Campos incompletos, el campo cilindraje es querido.";
		String fieldTipoVehiculoValueMoto = "MOTO";
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withTipoVehiculo(fieldTipoVehiculoValueMoto);
		
		// Act - Assert
		TestBase.assertThrows(ingresoTestDataBuilder::build, ExceptionRequiredValue.class,
				messageTipoVehiculoRequired);
	}
}
