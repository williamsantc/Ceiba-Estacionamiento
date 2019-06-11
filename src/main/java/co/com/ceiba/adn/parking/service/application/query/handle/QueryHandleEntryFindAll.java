package co.com.ceiba.adn.parking.service.application.query.handle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.model.Entry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;

@Component
public class QueryHandleEntryFindAll {

	@Autowired
	private QueryPortEntry queryPortEntry;
	
	public List<Entry> handle() {
		return this.queryPortEntry.findAll();
	}
}
