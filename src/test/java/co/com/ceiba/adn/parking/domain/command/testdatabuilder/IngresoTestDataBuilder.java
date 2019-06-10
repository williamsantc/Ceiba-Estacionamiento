package co.com.ceiba.adn.parking.domain.command.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.adn.parking.service.domain.model.Ingreso;

public class IngresoTestDataBuilder {

	private Long id;

	private String placa;

	private String tipoVehiculo;

	private String cilindraje;

	private Calendar registroEntrada;

	public IngresoTestDataBuilder() {
		this.placa = "ERT345";
		this.tipoVehiculo = "CARRO";
		this.registroEntrada = Calendar.getInstance();
	}

	public IngresoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public IngresoTestDataBuilder withTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public IngresoTestDataBuilder withCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public IngresoTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public IngresoTestDataBuilder withRegistroEntrada(Calendar registroEntrada) {
		this.registroEntrada = registroEntrada;
		return this;
	}

	public Ingreso build() {
		return new Ingreso(this.id, this.placa, this.tipoVehiculo, this.cilindraje, this.registroEntrada);
	}
}
