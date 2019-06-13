package co.com.ceiba.adn.parking.domain.command.strategy;

import java.util.Calendar;

import co.com.ceiba.adn.parking.domain.model.Entry;

public class ECalculatePaymentCar implements IECalculatePayment {

	private static final int MAX_HOURS_OF_DAY = 9;
	private static final long PARSE_TO_HOURS = 1000L * 3600L;
	private static final long DAY_AS_HOURS = 24L;
	private static final long PARSE_TO_DAYS = PARSE_TO_HOURS * DAY_AS_HOURS;

	private static final Double HOUR_PRICE_CAR = 1000D;
	private static final Double DAY_PRICE_CAR = 8000D;

	@Override
	public Double calculatePayment(Entry entry) {
		Calendar entryTime = entry.getEntryTime();
		Calendar currentTime = Calendar.getInstance();

		int daysInParking = (int) ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_DAYS);
		int hoursInParking = (int) ((currentTime.getTimeInMillis() - entryTime.getTimeInMillis()) / PARSE_TO_HOURS);

		hoursInParking -= daysInParking * DAY_AS_HOURS;

		if (daysInParking >= MAX_HOURS_OF_DAY) {
			daysInParking++;
			hoursInParking = 0;
		}

		return (DAY_PRICE_CAR * daysInParking) + (HOUR_PRICE_CAR * hoursInParking);
	}

}
