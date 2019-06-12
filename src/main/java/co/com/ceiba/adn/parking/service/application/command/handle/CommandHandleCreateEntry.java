package co.com.ceiba.adn.parking.service.application.command.handle;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.common.application.CommandResponse;
import co.com.ceiba.adn.parking.common.application.handle.CommandHandleResponse;
import co.com.ceiba.adn.parking.service.application.command.CommandEntry;
import co.com.ceiba.adn.parking.service.application.command.factory.FactoryEntry;
import co.com.ceiba.adn.parking.service.domain.command.serve.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.service.domain.model.Entry;

@Component
public class CommandHandleCreateEntry implements CommandHandleResponse<CommandEntry, CommandResponse<Long>> {

	private CommandServiceCreateEntry commandServiceEntry;
	
	private FactoryEntry factoryEntry = FactoryEntry.getInstance();
	
	public CommandHandleCreateEntry (CommandServiceCreateEntry commandServiceEntry) {
		this.commandServiceEntry = commandServiceEntry;
	}
	
	@Override
	public CommandResponse<Long> exec(CommandEntry commandEntry) {
		Entry entry = factoryEntry.create(commandEntry);
		return new CommandResponse<>(this.commandServiceEntry.exec(entry));
	}

}
