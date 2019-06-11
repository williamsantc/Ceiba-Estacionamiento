package co.com.ceiba.adn.parking.service.infrastructure.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ceiba.adn.parking.common.application.CommandResponse;
import co.com.ceiba.adn.parking.service.application.command.CommandEntry;
import co.com.ceiba.adn.parking.service.application.command.handle.CommandHandleCreateEntry;

@RestControllerAdvice
@RestController
@RequestMapping("/entry")
@CrossOrigin("*")
public class CommandIngresoController {
	
	@Autowired
	private CommandHandleCreateEntry commandHandleCreateEntry;

	@PostMapping
	public CommandResponse<Long> create (@RequestBody CommandEntry commandEntry) {
		return commandHandleCreateEntry.exec(commandEntry);
	}
}
