package co.com.ceiba.adn.parking.domain.command.strategy;

import co.com.ceiba.adn.parking.domain.model.Entry;

public interface IECalculatePayment {

	Double calculatePayment(Entry entry);
}
