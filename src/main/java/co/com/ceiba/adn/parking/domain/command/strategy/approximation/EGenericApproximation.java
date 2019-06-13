package co.com.ceiba.adn.parking.domain.command.strategy.approximation;

public class EGenericApproximation implements IEApproximation {
	@Override
	public int approximate(double number) {
		int integerPart = (int) number;
		return number > integerPart ? integerPart + 1 : integerPart;
	}
}
