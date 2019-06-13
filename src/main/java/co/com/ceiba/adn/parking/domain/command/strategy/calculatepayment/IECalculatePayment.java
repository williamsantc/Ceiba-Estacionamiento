package co.com.ceiba.adn.parking.domain.command.strategy.calculatepayment;

import co.com.ceiba.adn.parking.domain.model.Entry;

public interface IECalculatePayment {

	Double calculatePayment(Entry entry);
}
