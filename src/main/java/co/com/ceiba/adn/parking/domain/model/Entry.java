package co.com.ceiba.adn.parking.domain.model;

import static co.com.ceiba.adn.common.domain.ArgValidator.validateNumeric;
import static co.com.ceiba.adn.common.domain.ArgValidator.validateRequired;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.exception.ExceptionWrongVehicleType;

public class Entry {

	// Constants

	private static final String MESSAGE_LICENCEPLATE_REQUIRED = "Campos incompletos, el campo placa es querido.";
	private static final String MESSAGE_VEHICLE_TYPE_REQUIRED = "Campos incompletos, el campo tipo vehiculo es querido.";
	private static final String MESSAGE_ENGINE_DISPLACEMENT_REQUIRED = "Campos incompletos, el campo cilindraje es querido.";
	private static final String MESSAGE_ENGINE_DISPLACEMENT_WRONG_TYPE = "Error, el campo cilindraje no posee un dato válido.";
	private static final String MESSAGE_WRONG_VEHICLE_TYPE = "Error, el campo tipo vehiculo posee un dato no válido.";
	
	private static final String FIELD_VEHICLETYPE_VALUE_MOTORCYCLE = "MOTORCYCLE";
	private static final String FIELD_VEHICLETYPE_VALUE_CAR = "CAR";

	private Long id;

	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;

	public Entry(String licencePlate, String vehicleType, String engineDisplacement, Calendar entryTime) {
		validateRequired(licencePlate, MESSAGE_LICENCEPLATE_REQUIRED);
		validateRequired(vehicleType, MESSAGE_VEHICLE_TYPE_REQUIRED);
		this.validateVehicleType(vehicleType);
		if (vehicleType.equalsIgnoreCase(FIELD_VEHICLETYPE_VALUE_MOTORCYCLE)) {
			validateRequired(engineDisplacement, MESSAGE_ENGINE_DISPLACEMENT_REQUIRED);
			validateNumeric(engineDisplacement, MESSAGE_ENGINE_DISPLACEMENT_WRONG_TYPE);
		}
		this.licencePlate = licencePlate;
		this.vehicleType = vehicleType;
		this.engineDisplacement = engineDisplacement;
		this.entryTime = entryTime;
	}

	private void validateVehicleType(String vehicleType) {
		if (!vehicleType.contentEquals(FIELD_VEHICLETYPE_VALUE_CAR)
				&& !vehicleType.contentEquals(FIELD_VEHICLETYPE_VALUE_MOTORCYCLE)) {
			throw new ExceptionWrongVehicleType(MESSAGE_WRONG_VEHICLE_TYPE);
		}
	}

	public Long getId() {
		return id;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public String getVehicleType() {
		return vehicleType;
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
