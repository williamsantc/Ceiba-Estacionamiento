package co.com.ceiba.adn.parking.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.adn.parking.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.domain.command.service.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.domain.query.port.QueryPortEntry;

@Configuration
public class BeanService {

	@Bean
	public CommandServiceCreateEntry commandServiceCreateEntry(QueryPortEntry queryPortEntry, CommandPortEntry commandPortEntry) {
		return new CommandServiceCreateEntry(queryPortEntry, commandPortEntry);
	}
}
