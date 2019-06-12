package co.com.ceiba.adn.parking.domain.command.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.model.Entry;

public class EntryTestDataBuilder {

	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;

	public EntryTestDataBuilder() {
		this.licencePlate = "ERT345";
		this.vehicleType = "CAR";
		this.entryTime = Calendar.getInstance();
	}

	public EntryTestDataBuilder withLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
		return this;
	}

	public EntryTestDataBuilder withVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
		return this;
	}

	public EntryTestDataBuilder withEngineDisplacement(String engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
		return this;
	}

	public EntryTestDataBuilder withEntryTime(Calendar entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public Entry build() {
		return new Entry(this.licencePlate, this.vehicleType, this.engineDisplacement, this.entryTime);
	}
}
