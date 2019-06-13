package co.com.ceiba.adn.parking.application.command.handle;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.common.application.CommandResponse;
import co.com.ceiba.adn.common.application.handle.CommandHandleResponse;
import co.com.ceiba.adn.parking.application.command.CommandEntry;
import co.com.ceiba.adn.parking.application.command.factory.FactoryEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.domain.model.Entry;

@Component
public class CommandHandleCreateEntry implements CommandHandleResponse<CommandEntry, CommandResponse<Long>> {

	private CommandServiceCreateEntry commandServiceCreateEntry;
	
	private FactoryEntry factoryEntry = FactoryEntry.getInstance();
	
	public CommandHandleCreateEntry (CommandServiceCreateEntry commandServiceEntry) {
		this.commandServiceCreateEntry = commandServiceEntry;
	}
	
	@Override
	public CommandResponse<Long> exec(CommandEntry commandEntry) {
		Entry entry = factoryEntry.create(commandEntry);
		return new CommandResponse<>(this.commandServiceCreateEntry.exec(entry));
	}

}
