package co.com.ceiba.adn.parking.service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.command.serve.CommandServiceCreateEntry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;

@Configuration
public class BeanService {

	@Bean
	public CommandServiceCreateEntry commandServiceCreateEntry(QueryPortEntry queryPortEntry, CommandPortEntry commandPortEntry) {
		return new CommandServiceCreateEntry(queryPortEntry, commandPortEntry);
	}
}
