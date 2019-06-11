package co.com.ceiba.adn.parking.common.domain;

import java.util.List;

import co.com.ceiba.adn.parking.common.domain.exception.ExceptionEmpty;
import co.com.ceiba.adn.parking.common.domain.exception.ExceptionInvalidValue;
import co.com.ceiba.adn.parking.common.domain.exception.ExceptionLengthValue;
import co.com.ceiba.adn.parking.common.domain.exception.ExceptionRequiredValue;

public final class ArgValidator {

	private ArgValidator() {

	}

	public static void validateRequired(Object value, String message) {
		if (value == null) {
			throw new ExceptionRequiredValue(message);
		}
	}

	public static void validateLength(String value, int length, String message) {
		if (value.length() < length) {
			throw new ExceptionLengthValue(message);
		}
	}

	public static void validateRequired(String value, String message) {
		if (value == null || value.isEmpty()) {
			throw new ExceptionRequiredValue(message);
		}
	}

	public static <T> void validateListNotEmpty(List<T> list, String message) {
		if (list == null || list.isEmpty()) {
			throw new ExceptionEmpty(message);
		}
	}

	public static void validatePositive(Double value, String message) {
		if (value <= 0) {
			throw new ExceptionInvalidValue(message);
		}
	}

	public static void validateEqualTo(Double value, Double expectedValue, String message) {
		if (!value.equals(expectedValue)) {
			throw new ExceptionInvalidValue(message);
		}
	}

	public static void validateNumeric(String value, String message) {
		try {
			Long.parseLong(value);
		} catch (NumberFormatException numberFormatException) {
			throw new ExceptionInvalidValue(message);
		}
	}
}
