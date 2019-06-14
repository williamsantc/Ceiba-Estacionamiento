package co.com.ceiba.adn.parking.domain.command.service;

import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.command.strategy.calculatepayment.ContextCalculatePayment;
import co.com.ceiba.adn.parking.domain.command.strategy.calculatepayment.IECalculatePayment;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotFound;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

public class CommandServiceDispatchVehicle {

	private CommandRepositoryEntry commandRepositoryEntry;

	private QueryRepositoryEntry queryRepositoryEntry;
	
	private static final String MESSAGE_ENTRY_NOT_FOUND = "No se encontró ningún vehiculo en el parqueadero con la placa proporcionada";

	public CommandServiceDispatchVehicle(QueryRepositoryEntry queryPortEntry,
			CommandRepositoryEntry commandPortRepositoryEntry) {
		this.queryRepositoryEntry = queryPortEntry;
		this.commandRepositoryEntry = commandPortRepositoryEntry;
	}

	public Double exec(String licencePlate) {
		Entry entry = queryRepositoryEntry.findByLicencePlate(licencePlate);
		if (entry == null) {
			throw new ExceptionEntryNotFound(MESSAGE_ENTRY_NOT_FOUND);
		}
		commandRepositoryEntry.deleteEntry(entry);
		return calculatePayment(entry);
	}

	private Double calculatePayment(Entry entry) {
		IECalculatePayment calculatePaymentStrategy = ContextCalculatePayment.getStrategy(entry.getVehicleType());
		return calculatePaymentStrategy.calculatePayment(entry);
	}

}
