package co.com.ceiba.adn.parking.domain.command.service;

import java.util.Calendar;

import co.com.ceiba.adn.parking.application.exception.ExceptionEntryNotAllowed;
import co.com.ceiba.adn.parking.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.port.QueryPortEntry;

public class CommandServiceCreateEntry {

	private static final String FIELD_VEHICLETYPE_VALUE_CAR = "CAR";
	private static final String FIELD_VEHICLETYPE_VALUE_MOTORCLYCLE = "MOTORCYCLE";
	private static final int LIMIT_CAR_COUNT = 20;
	private static final int LIMIT_MOTORCYCLE_COUNT = 10;
	private static final String LICENCEPLATE_START_WITH = "A";
	private static final String MESSAGE_VEHICLE_LIMIT_REACHED = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
	private static final String MESSAGE_LICENCEPLATE_NOT_ALLOWED = "Ingreso no permitido, el tipo de placa indicado solo tiene permitido el ingreso los días domingo y lunes.";

	private CommandPortEntry commandPortEntry;

	private QueryPortEntry queryPortEntry;

	public CommandServiceCreateEntry(QueryPortEntry queryPortEntry, CommandPortEntry commandPortEntry) {
		this.queryPortEntry = queryPortEntry;
		this.commandPortEntry = commandPortEntry;
	}

	public Long exec(Entry entry) {
		this.validateAvailabilityEntryByLicencePlate(entry.getLicencePlate(), entry.getEntryTime());
		this.validateDisponibilidadEntryByVehicleType(entry.getVehicleType());
		return this.commandPortEntry.insertEntry(entry).getId();
	}

	private void validateDisponibilidadEntryByVehicleType(String vehicleType) {
		int countVehicleType = this.queryPortEntry.countByVehicleType(vehicleType);
		if ((vehicleType.equalsIgnoreCase(FIELD_VEHICLETYPE_VALUE_CAR) && countVehicleType >= LIMIT_CAR_COUNT)
				|| (vehicleType.equalsIgnoreCase(FIELD_VEHICLETYPE_VALUE_MOTORCLYCLE)
						&& countVehicleType >= LIMIT_MOTORCYCLE_COUNT)) {
			throw new ExceptionEntryNotAllowed(MESSAGE_VEHICLE_LIMIT_REACHED);
		}
	}

	private void validateAvailabilityEntryByLicencePlate(String licencePlate, Calendar date) {
		int day = date.get(Calendar.DAY_OF_WEEK);
		if (licencePlate.startsWith(LICENCEPLATE_START_WITH) && day > Calendar.MONDAY) {
			throw new ExceptionEntryNotAllowed(MESSAGE_LICENCEPLATE_NOT_ALLOWED);
		}
	}
}
