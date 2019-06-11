package co.com.ceiba.adn.parking.service.application.command.factory;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.application.command.CommandEntry;
import co.com.ceiba.adn.parking.service.domain.model.Entry;

@Component
public class FactoryEntry {

	private static final FactoryEntry INSTANCE = new FactoryEntry();

	public static FactoryEntry getInstance() {
		return INSTANCE;
	}

	public Entry create(CommandEntry commandEntry) {
		return new Entry(commandEntry.getLicencePlate(), commandEntry.getVehicleType(),
				commandEntry.getEngineDisplacement(), Calendar.getInstance());
	}
}
