package co.com.ceiba.adn.parking.domain.command.repository;

import co.com.ceiba.adn.parking.domain.model.Entry;

public interface CommandRepositoryEntry {

	Entry insertEntry(Entry entry);

	void deleteEntry(Entry entry);
}
