package co.com.ceiba.adn.parking.infrastructure.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ceiba.adn.common.application.CommandResponse;
import co.com.ceiba.adn.parking.application.command.CommandEntry;
import co.com.ceiba.adn.parking.application.command.handle.CommandHandleCreateEntry;
import co.com.ceiba.adn.parking.application.command.handle.CommandHandleDispatchVehicle;

@RestControllerAdvice
@RestController
@RequestMapping("/entry")
@CrossOrigin("*")
public class CommandEntryController {

	private CommandHandleCreateEntry commandHandleCreateEntry;
	
	private CommandHandleDispatchVehicle commandHandleDispatchVehicle;

	@Autowired
	public CommandEntryController(CommandHandleCreateEntry commandHandleCreateEntry, CommandHandleDispatchVehicle commandHandleDispatchVehicle) {
		this.commandHandleCreateEntry = commandHandleCreateEntry;
		this.commandHandleDispatchVehicle = commandHandleDispatchVehicle;
	}

	@PostMapping
	public CommandResponse<Long> create(@RequestBody CommandEntry commandEntry) {
		return commandHandleCreateEntry.exec(commandEntry);
	}
	
	@PutMapping
	public CommandResponse<Double> dispatch(@RequestBody CommandEntry commandEntry) {
		return commandHandleDispatchVehicle.exec(commandEntry);
	}
}
