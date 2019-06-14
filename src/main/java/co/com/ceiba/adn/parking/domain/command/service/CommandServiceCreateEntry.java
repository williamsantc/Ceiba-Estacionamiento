package co.com.ceiba.adn.parking.domain.command.service;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryExist;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotAllowed;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

public class CommandServiceCreateEntry {

	private static final String FIELD_VEHICLETYPE_VALUE_CAR = "CAR";
	private static final String FIELD_VEHICLETYPE_VALUE_MOTORCLYCLE = "MOTORCYCLE";
	private static final int LIMIT_CAR_COUNT = 20;
	private static final int LIMIT_MOTORCYCLE_COUNT = 10;
	private static final String LICENCEPLATE_START_WITH = "A";
	private static final String MESSAGE_VEHICLE_LIMIT_REACHED = "Ingreso no permitido, no hay mas cupo en el parqueadero.";
	private static final String MESSAGE_LICENCEPLATE_NOT_ALLOWED = "Ingreso no permitido, el tipo de placa indicado solo tiene permitido el ingreso los días domingo y lunes.";
	private static final String MESSAGE_ENTRY_FOUND = "Ya se encuentra un vehiculo en el parqueadero con la placa proporcionada.";
	
	private CommandRepositoryEntry commandRepositoryEntry;

	private QueryRepositoryEntry queryRepositoryEntry;

	public CommandServiceCreateEntry(QueryRepositoryEntry queryRepositoryEntry, CommandRepositoryEntry commandRepositoryEntry) {
		this.queryRepositoryEntry = queryRepositoryEntry;
		this.commandRepositoryEntry = commandRepositoryEntry;
	}

	public Long exec(Entry entry) {
		Entry entryCheck = queryRepositoryEntry.findByLicencePlate(entry.getLicencePlate());
		if (entryCheck != null) {
			throw new ExceptionEntryExist(MESSAGE_ENTRY_FOUND);
		}
		this.validateAvailabilityEntryByLicencePlate(entry.getLicencePlate(), entry.getEntryTime());
		this.validateAvailabilityEntryByVehicleType(entry.getVehicleType());
		return this.commandRepositoryEntry.insertEntry(entry).getId();
	}

	private void validateAvailabilityEntryByVehicleType(String vehicleType) {
		int countVehicleType = this.queryRepositoryEntry.countByVehicleType(vehicleType);
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
