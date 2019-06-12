package co.com.ceiba.adn.common;

import java.util.ArrayList;

import org.junit.Test;

import co.com.ceiba.adn.TestBase;
import co.com.ceiba.adn.common.domain.ArgValidator;
import co.com.ceiba.adn.common.domain.exception.ExceptionEmpty;
import co.com.ceiba.adn.common.domain.exception.ExceptionInvalidValue;
import co.com.ceiba.adn.common.domain.exception.ExceptionLengthValue;
import co.com.ceiba.adn.common.domain.exception.ExceptionRequiredValue;

public class ArgValidatorTest {

	private static final String MESSAGE = "ERROR";
	
	@Test
	public void validateRequiredObject() {

		// Arrange
		Object value = null;

		// Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validateRequired(value, MESSAGE), ExceptionRequiredValue.class);
	}

	@Test
	public void validateLength() {

		// Arrange
		String value = "SOMETHING";
		int length = 10;
		
		// Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validateLength(value, length, MESSAGE), ExceptionLengthValue.class);
	}
	
	@Test
	public void validateListNotEmpty() {
		
		// Arrange
		ArrayList<Object> listEmpty = new ArrayList<>();
		ArrayList<Object> listNull = null;
		
		// Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validateListNotEmpty(listEmpty, MESSAGE), ExceptionEmpty.class);
		TestBase.assertThrows(() -> ArgValidator.validateListNotEmpty(listNull, MESSAGE), ExceptionEmpty.class);
	}
	
	@Test
	public void validatePositive () {
		
		// Arrange
		Double value = -1D;
		
		//Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validatePositive(value, MESSAGE), ExceptionInvalidValue.class);
	}
	
	@Test
	public void validateEqualTo () {
		
		// Arrange
		Double value = 1D;
		Double expectedValue = 2D;
		
		// Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validateEqualTo(value, expectedValue, MESSAGE), ExceptionInvalidValue.class);
	}
	
	@Test
	public void validateNumeric () {
		// Arrange
		String number = "NUMBER";
		
		// Act - Assert
		TestBase.assertThrows(() -> ArgValidator.validateNumeric(number, MESSAGE), ExceptionInvalidValue.class);
	}
}
