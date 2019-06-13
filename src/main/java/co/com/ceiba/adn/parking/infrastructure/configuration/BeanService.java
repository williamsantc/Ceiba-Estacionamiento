package co.com.ceiba.adn.parking.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.adn.parking.domain.command.repository.CommandRepositoryEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceDispatchVehicle;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

@Configuration
public class BeanService {

	@Bean
	public CommandServiceCreateEntry commandServiceCreateEntry(QueryRepositoryEntry queryPortEntry,
			CommandRepositoryEntry commandPortEntry) {
		return new CommandServiceCreateEntry(queryPortEntry, commandPortEntry);
	}

	@Bean
	public CommandServiceDispatchVehicle commandServiceDispatchVehicle(QueryRepositoryEntry queryPortEntry,
			CommandRepositoryEntry commandPortRepositoryEntry) {
		return new CommandServiceDispatchVehicle(queryPortEntry, commandPortRepositoryEntry);
	}
}
