package co.com.ceiba.adn.parking.infrastructure.command.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

public class EntityEntryTestDataBuilder {

	private Long id;

	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;

	public EntityEntryTestDataBuilder() {
		this.id = 1L;
		this.licencePlate = "ERT345";
		this.vehicleType = "CAR";
		this.entryTime = Calendar.getInstance();
	}

	public EntityEntryTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public EntityEntryTestDataBuilder withLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
		return this;
	}

	public EntityEntryTestDataBuilder withVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
		return this;
	}

	public EntityEntryTestDataBuilder withEngineDisplacement(String engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
		return this;
	}

	public EntityEntryTestDataBuilder withEntryTime(Calendar entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public EntityEntry build() {
		return new EntityEntry(this.id, this.licencePlate, this.vehicleType, this.engineDisplacement, this.entryTime);
	}
}
