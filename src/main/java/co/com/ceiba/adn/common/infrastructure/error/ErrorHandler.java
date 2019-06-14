package co.com.ceiba.adn.common.infrastructure.error;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.ceiba.adn.common.domain.exception.ExceptionEmpty;
import co.com.ceiba.adn.common.domain.exception.ExceptionInvalidValue;
import co.com.ceiba.adn.common.domain.exception.ExceptionLengthValue;
import co.com.ceiba.adn.common.domain.exception.ExceptionRequiredValue;
import co.com.ceiba.adn.common.infrastructure.exception.TechnicalException;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryExist;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotAllowed;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotFound;
import co.com.ceiba.adn.parking.domain.exception.ExceptionWrongVehicleType;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(ErrorHandler.class);

	private static final String MESSAGE_ERROR_CONTACT_ADMIN = "Ocurrió un error favor contactar al administrador.";

	private static final ConcurrentHashMap<String, Integer> STATE_CODES = new ConcurrentHashMap<>();

	public ErrorHandler() {
		STATE_CODES.put(ExceptionEmpty.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
		STATE_CODES.put(ExceptionInvalidValue.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		STATE_CODES.put(ExceptionRequiredValue.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		STATE_CODES.put(ExceptionLengthValue.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		STATE_CODES.put(TechnicalException.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR.value());

		// Application Exceptions
		STATE_CODES.put(ExceptionEntryNotFound.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
		STATE_CODES.put(ExceptionEntryExist.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		STATE_CODES.put(ExceptionEntryNotAllowed.class.getSimpleName(), HttpStatus.FORBIDDEN.value());
		STATE_CODES.put(ExceptionEntryExist.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		STATE_CODES.put(ExceptionWrongVehicleType.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleAllExceptions(Exception exception) {

		ResponseEntity<Error> response;

		String exceptionName = exception.getClass().getSimpleName();
		String message = exception.getMessage();

		Integer code = STATE_CODES.get(exceptionName);

		if (code == null) {
			LOGGER_ERROR.error(exceptionName, message);
			Error error = new Error(exceptionName, MESSAGE_ERROR_CONTACT_ADMIN);
			response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Error error = new Error(exceptionName, message);
			response = new ResponseEntity<>(error, HttpStatus.valueOf(code));
		}
		
		return response;
	}
}
