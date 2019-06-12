package co.com.ceiba.adn.parking.domain.command.port;

import co.com.ceiba.adn.parking.domain.model.Entry;

public interface CommandPortEntry {

	Entry insertEntry(Entry entry);

	void deleteEntry(Entry entry);
}
