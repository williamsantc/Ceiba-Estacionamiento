package co.com.ceiba.adn.parking.application.command.handle;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.common.application.CommandResponse;
import co.com.ceiba.adn.common.application.handle.CommandHandleResponse;
import co.com.ceiba.adn.parking.application.command.CommandEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceDispatchVehicle;

@Component
public class CommandHandleDispatchVehicle implements CommandHandleResponse<CommandEntry, CommandResponse<Double>> {

	private CommandServiceDispatchVehicle commandServiceDispatchVehicle;
	
	public CommandHandleDispatchVehicle (CommandServiceDispatchVehicle commandServiceDispatchVehicle) {
		this.commandServiceDispatchVehicle = commandServiceDispatchVehicle;
	}
	
	@Override
	public CommandResponse<Double> exec(CommandEntry commandEntry) {
		return new CommandResponse<>(this.commandServiceDispatchVehicle.exec(commandEntry.getLicencePlate()));
	}

}
