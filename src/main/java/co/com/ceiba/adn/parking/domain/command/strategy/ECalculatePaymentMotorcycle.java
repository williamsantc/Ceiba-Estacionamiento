package co.com.ceiba.adn.parking.domain.command.strategy;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.model.Entry;

public class ECalculatePaymentMotorcycle implements IECalculatePayment {

	private static final int MAX_HOURS_OF_DAY = 9;
	private static final long PARSE_TO_HOURS = 1000L * 3600L;
	private static final long DAY_AS_HOURS = 24L;
	private static final long PARSE_TO_DAYS = PARSE_TO_HOURS * DAY_AS_HOURS;
	private static final Double HOUR_PRICE_MOTORCYCLE = 500D;
	private static final Double DAY_PRICE_MOTORCYCLE = 4000D;
	private static final Double HIGH_ENGINE_DISPLACEMENT = 500D;
	private static final Double SURCHARGE_HIGH_ENGINE_DISPLACEMENT = 2000D;

	@Override
	public Double calculatePayment(Entry entry) {
		Calendar entryTime = entry.getEntryTime();
		Calendar currentTime = Calendar.getInstance();

		int daysInParking = (int) ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_DAYS);
		int hoursInParking = (int) ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_HOURS);

		hoursInParking -= daysInParking * DAY_AS_HOURS;

		long engineDisplacement = Long.parseLong(entry.getEngineDisplacement());

		if (daysInParking >= MAX_HOURS_OF_DAY) {
			daysInParking++;
			hoursInParking = 0;
		}

		Double paymentValue = (DAY_PRICE_MOTORCYCLE * daysInParking) + (HOUR_PRICE_MOTORCYCLE * hoursInParking);

		if (engineDisplacement > HIGH_ENGINE_DISPLACEMENT) {
			paymentValue += SURCHARGE_HIGH_ENGINE_DISPLACEMENT;
		}

		return paymentValue;
	}

}
