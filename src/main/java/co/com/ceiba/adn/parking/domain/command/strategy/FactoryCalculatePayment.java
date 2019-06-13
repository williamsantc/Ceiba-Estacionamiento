package co.com.ceiba.adn.parking.domain.command.strategy;

import java.util.HashMap;
import java.util.Map;

public class FactoryCalculatePayment {

	private static final String KEY_MOTORCYCLE = "MOTORCYCLE";
	private static final String KEY_CAR = "CAR";

	private static final Map<String, IECalculatePayment> STRATEGY_CALCULATE_PAYMENT = new HashMap<>();

	private FactoryCalculatePayment() {
		throw new IllegalStateException("Factory of Calculate Payment Strategy");
	}

	static {
		STRATEGY_CALCULATE_PAYMENT.put(KEY_MOTORCYCLE, new ECalculatePaymentMotorcycle());
		STRATEGY_CALCULATE_PAYMENT.put(KEY_CAR, new ECalculatePaymentCar());
	}

	public static IECalculatePayment getStrategy(String strategy) {
		return STRATEGY_CALCULATE_PAYMENT.get(strategy);
	}
}
