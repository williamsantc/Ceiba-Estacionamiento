package co.com.ceiba.adn.parking.domain.command.strategy.calculatepayment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContextCalculatePayment {

	private static final String KEY_MOTORCYCLE = "MOTORCYCLE";
	private static final String KEY_CAR = "CAR";

	private static final Map<String, IECalculatePayment> CALCULATE_PAYMENT_STRATEGIES = new ConcurrentHashMap<>();

	private ContextCalculatePayment() {
		throw new IllegalStateException("Factory of CalculatePayment Strategies");
	}

	static {
		CALCULATE_PAYMENT_STRATEGIES.put(KEY_MOTORCYCLE, new ECalculatePaymentMotorcycle());
		CALCULATE_PAYMENT_STRATEGIES.put(KEY_CAR, new ECalculatePaymentCar());
	}

	public static IECalculatePayment getStrategy(String strategy) {
		return CALCULATE_PAYMENT_STRATEGIES.get(strategy);
	}
}
