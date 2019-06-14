package co.com.ceiba.adn.parking.domain.command.strategy.calculatepayment;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.command.strategy.approximation.ContextApproximation;
import co.com.ceiba.adn.parking.domain.command.strategy.approximation.IEApproximation;
import co.com.ceiba.adn.parking.domain.model.Entry;

public class ECalculatePaymentMotorcycle implements IECalculatePayment {

	private static final int MAX_HOURS_OF_DAY = 9;
	private static final Double PARSE_TO_HOURS = 1000D * 3600D;
	private static final long DAY_AS_HOURS = 24L;
	private static final Double PARSE_TO_DAYS = PARSE_TO_HOURS * DAY_AS_HOURS;
	private static final Double HOUR_PRICE_MOTORCYCLE = 500D;
	private static final Double DAY_PRICE_MOTORCYCLE = 4000D;
	private static final int HIGH_ENGINE_DISPLACEMENT = 500;
	private static final Double SURCHARGE_HIGH_ENGINE_DISPLACEMENT = 2000D;

	private static final String APPROXIMATION_STRATEGY = "GENERIC";

	@Override
	public Double calculatePayment(Entry entry) {
		Calendar entryTime = entry.getEntryTime();
		Calendar currentTime = Calendar.getInstance();

		int daysInParking = (int) ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_DAYS);
		double hoursInParking = ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_HOURS);

		hoursInParking -= daysInParking * DAY_AS_HOURS;

		long engineDisplacement = Long.parseLong(entry.getEngineDisplacement());

		if (hoursInParking >= MAX_HOURS_OF_DAY) {
			daysInParking++;
			hoursInParking = 0;
		}
		
		IEApproximation approximationStrategy = ContextApproximation.getStrategy(APPROXIMATION_STRATEGY);
		int approximatedHours = approximationStrategy.approximate(hoursInParking);

		Double paymentValue = (DAY_PRICE_MOTORCYCLE * daysInParking) + (HOUR_PRICE_MOTORCYCLE * approximatedHours);

		if (engineDisplacement > HIGH_ENGINE_DISPLACEMENT) {
			paymentValue += SURCHARGE_HIGH_ENGINE_DISPLACEMENT;
		}

		return paymentValue;
	}

}
