package co.com.ceiba.adn.parking.service.domain.command.port;

import co.com.ceiba.adn.parking.service.domain.model.Entry;

public interface CommandPortEntry {

	Entry insertEntry(Entry entry);
}
