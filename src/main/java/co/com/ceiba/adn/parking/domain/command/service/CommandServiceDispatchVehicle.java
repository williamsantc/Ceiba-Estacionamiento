package co.com.ceiba.adn.parking.domain.command.service;

import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.command.strategy.FactoryCalculatePayment;
import co.com.ceiba.adn.parking.domain.command.strategy.IECalculatePayment;
import co.com.ceiba.adn.parking.domain.model.Entry;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

public class CommandServiceDispatchVehicle {

	private CommandRepositoryEntry commandPortRepositoryEntry;

	private QueryRepositoryEntry queryPortRepositoryEntry;

	public CommandServiceDispatchVehicle(QueryRepositoryEntry queryPortEntry,
			CommandRepositoryEntry commandPortRepositoryEntry) {
		this.queryPortRepositoryEntry = queryPortEntry;
		this.commandPortRepositoryEntry = commandPortRepositoryEntry;
	}

	public Double exec(String licencePlate) {
		Entry entry = queryPortRepositoryEntry.findByLicencePlate(licencePlate);
		commandPortRepositoryEntry.deleteEntry(entry);
		return calculatePayment(entry);
	}

	private Double calculatePayment(Entry entry) {
		IECalculatePayment calculatePaymentStrategy = FactoryCalculatePayment.getStrategy(entry.getVehicleType());
		return calculatePaymentStrategy.calculatePayment(entry);
	}

}
