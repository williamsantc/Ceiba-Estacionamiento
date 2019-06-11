package co.com.ceiba.adn.parking.service.domain.model;

import static co.com.ceiba.adn.parking.common.domain.ArgValidator.validateRequired;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
		if (vehicleType != null && vehicleType.equalsIgnoreCase(FIELD_VEHICLETYPE_VALUE_MOTORCYCLE)) {
			validateRequired(engineDisplacement, MESSAGE_ENGINE_DISPLACEMENT_REQUIRED);
		}
		this.licencePlate = licencePlate;
		this.vehicleType = vehicleType;
		this.engineDisplacement = engineDisplacement;
		this.entryTime = entryTime;
	}

}
