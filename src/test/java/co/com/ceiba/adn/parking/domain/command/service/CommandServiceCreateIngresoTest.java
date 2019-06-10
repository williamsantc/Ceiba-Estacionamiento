package co.com.ceiba.adn.parking.domain.command.service;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.adn.parking.domain.command.testdatabuilder.IngresoTestDataBuilder;
import co.com.ceiba.adn.parking.parking.TestBase;
import co.com.ceiba.adn.parking.service.application.exception.ExeptionIngresoNoPermitido;
import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortIngreso;
import co.com.ceiba.adn.parking.service.domain.command.serve.CommandServiceCreateIngreso;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortIngreso;

public class CommandServiceCreateIngresoTest {

	@Test
	public void validateInsertIngresoWithPlacaValueA() {
		// Arrange
		String messagePlacaNoPermitida = "Ingreso no permitido, el tipo de placa indicado solo tiene permitido el ingreso los días domingo y lunes.";

		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY); // Date greater than MONDAY

		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withPlaca("ASD123").withRegistroEntrada(date);
		Ingreso ingreso = ingresoTestDataBuilder.build();

		QueryPortIngreso queryPortIngreso = Mockito.mock(QueryPortIngreso.class);
		Mockito.when(queryPortIngreso.countByTipoVehiculo(Mockito.anyString())).thenReturn(4);

		CommandPortIngreso commandPortIngreso = Mockito.mock(CommandPortIngreso.class);
		Mockito.when(commandPortIngreso.insertIngreso(ingreso)).thenReturn(ingreso);

		CommandServiceCreateIngreso commandServiceCreateIngreso = new CommandServiceCreateIngreso(queryPortIngreso,
				commandPortIngreso);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateIngreso.exec(ingreso), ExeptionIngresoNoPermitido.class,
				messagePlacaNoPermitida);
	}

	@Test
	public void validateInsertIngresoWithCupoActualSuperadoCarro() {

		// Arrange
		String messageLimiteVehiculosAlcanzado = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldTipoVehiculoValueCarro = "CARRO";
		int limitCountCarros = 20;

		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withPlaca("DFR345").withTipoVehiculo(fieldTipoVehiculoValueCarro);
		Ingreso ingreso = ingresoTestDataBuilder.build();

		QueryPortIngreso queryPortIngreso = Mockito.mock(QueryPortIngreso.class);
		Mockito.when(queryPortIngreso.countByTipoVehiculo(Mockito.anyString())).thenReturn(limitCountCarros);

		CommandPortIngreso commandPortIngreso = Mockito.mock(CommandPortIngreso.class);
		Mockito.when(commandPortIngreso.insertIngreso(ingreso)).thenReturn(ingreso);

		CommandServiceCreateIngreso commandServiceCreateIngreso = new CommandServiceCreateIngreso(queryPortIngreso,
				commandPortIngreso);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateIngreso.exec(ingreso), ExeptionIngresoNoPermitido.class,
				messageLimiteVehiculosAlcanzado);

	}

	@Test
	public void validateInsertIngresoWithCupoActualSuperadoMoto() {

		// Arrange
		String messageLimiteVehiculosAlcanzado = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
		String fieldTipoVehiculoValueCarro = "MOTO";
		int limitCountMotos = 10;

		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder();
		ingresoTestDataBuilder.withPlaca("DFR345").withTipoVehiculo(fieldTipoVehiculoValueCarro).withCilindraje("100");
		Ingreso ingreso = ingresoTestDataBuilder.build();

		QueryPortIngreso queryPortIngreso = Mockito.mock(QueryPortIngreso.class);
		Mockito.when(queryPortIngreso.countByTipoVehiculo(Mockito.anyString())).thenReturn(limitCountMotos);

		CommandPortIngreso commandPortIngreso = Mockito.mock(CommandPortIngreso.class);
		Mockito.when(commandPortIngreso.insertIngreso(ingreso)).thenReturn(ingreso);

		CommandServiceCreateIngreso commandServiceCreateIngreso = new CommandServiceCreateIngreso(queryPortIngreso,
				commandPortIngreso);

		// Act - Assert
		TestBase.assertThrows(() -> commandServiceCreateIngreso.exec(ingreso), ExeptionIngresoNoPermitido.class,
				messageLimiteVehiculosAlcanzado);

	}
	
}
