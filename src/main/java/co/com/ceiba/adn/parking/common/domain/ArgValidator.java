package co.com.ceiba.adn.parking.common.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.ceiba.adn.parking.common.domain.exception.*;

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
			throw new ExceptionRequiredValue(message);
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

	public static void validateMinLength(Object value, int minLength, String message) {
		if (value.toString().length() < minLength) {
			throw new ExceptionLengthValue(message);
		}
	}

	public static void validateDateLowerThan(LocalDateTime initialDate, LocalDateTime finalDate, String message) {
		if (initialDate.toLocalDate().isAfter(finalDate.toLocalDate())) {
			throw new ExceptionInvalidValue(message);
		}
	}

	public static void validateLowerThan(Long initialNumber, Long finalNumber, String message) {
		if (initialNumber > finalNumber) {
			throw new ExceptionInvalidValue(message);
		}
	}

	public static void validateRegex(String value, String regex, String message) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);

		if (!matcher.matches()) {
			throw new ExceptionInvalidValue(message);
		}
	}

	public static <E extends Enum<E>> E isValid(String value, Class<E> enumToGet, String message) {
		E enumGot = null;
		if (null != value) {
			Optional<E> optionalResult = Arrays.stream(enumToGet.getEnumConstants())
					.filter(result -> result.toString().equals(value)).findFirst();

			if (optionalResult.isPresent()) {
				enumGot = optionalResult.get();
			} else {
				throw new ExceptionInvalidValue(message);
			}
		}
		return enumGot;
	}

	public static void validateNumeric(String value, String message) {
		try {
			Long.parseLong(value);
		} catch (NumberFormatException numberFormatException) {
			throw new ExceptionInvalidValue(message);
		}
	}
}
