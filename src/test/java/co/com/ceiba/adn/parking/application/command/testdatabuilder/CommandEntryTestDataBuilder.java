package co.com.ceiba.adn.parking.application.command.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.adn.parking.application.command.CommandEntry;

public class CommandEntryTestDataBuilder {

	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;

	public CommandEntryTestDataBuilder() {
		this.licencePlate = "ERT345";
		this.vehicleType = "CAR";
		this.entryTime = Calendar.getInstance();
	}

	public CommandEntryTestDataBuilder withLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
		return this;
	}

	public CommandEntryTestDataBuilder withVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
		return this;
	}

	public CommandEntryTestDataBuilder withEngineDisplacement(String engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
		return this;
	}

	public CommandEntryTestDataBuilder withEntryTime(Calendar entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public CommandEntry build() {
		return new CommandEntry(this.licencePlate, this.vehicleType, this.engineDisplacement, this.entryTime);
	}
}
