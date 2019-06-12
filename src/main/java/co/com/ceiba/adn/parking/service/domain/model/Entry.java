package co.com.ceiba.adn.parking.service.domain.model;

import static co.com.ceiba.adn.parking.common.domain.ArgValidator.validateRequired;

import java.util.Calendar;

public class Entry {	

	// Constants

	private static final String MESSAGE_LICENCEPLATE_REQUIRED = "Campos incompletos, el campo placa es querido.";
	private static final String MESSAGE_VEHICLE_TYPE_REQUIRED = "Campos incompletos, el campo tipo vehiculo es querido.";
	private static final String MESSAGE_ENGINE_DISPLACEMENT_REQUIRED = "Campos incompletos, el campo cilindraje es querido.";
	private static final String FIELD_VEHICLETYPE_VALUE_MOTORCYCLE = "MOTORCYCLE";

	private Long id;

	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;

	public Entry(String licencePlate, String vehicleType, String engineDisplacement, Calendar entryTime) {
		validateRequired(licencePlate, MESSAGE_LICENCEPLATE_REQUIRED);
		validateRequired(vehicleType, MESSAGE_VEHICLE_TYPE_REQUIRED);
		if (vehicleType.equalsIgnoreCase(FIELD_VEHICLETYPE_VALUE_MOTORCYCLE)) {
			validateRequired(engineDisplacement, MESSAGE_ENGINE_DISPLACEMENT_REQUIRED);
		}
		this.licencePlate = licencePlate;
		this.vehicleType = vehicleType;
		this.engineDisplacement = engineDisplacement;
		this.entryTime = entryTime;
	}

	public Entry() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getEngineDisplacement() {
		return engineDisplacement;
	}

	public void setEngineDisplacement(String engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}

	public Calendar getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Calendar entryTime) {
		this.entryTime = entryTime;
	}
	
	

}
