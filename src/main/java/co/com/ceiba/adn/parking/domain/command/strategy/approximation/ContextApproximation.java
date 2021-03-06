package co.com.ceiba.adn.parking.domain.command.strategy.approximation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContextApproximation {

	private static final String GENERIC_STRATEGY = "GENERIC";
	
	private static final Map<String, IEApproximation> STRATEGIES_APPROXIMATION = new ConcurrentHashMap<>();
	
	static {
		STRATEGIES_APPROXIMATION.put(GENERIC_STRATEGY, new EGenericApproximation());
	}
	
	private ContextApproximation() {
		throw new IllegalStateException("Factory of Approximation Strategies");
	}
	
	public static IEApproximation getStrategy(String strategy) {
		return STRATEGIES_APPROXIMATION.get(strategy);
	}
}
