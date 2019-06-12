package co.com.ceiba.adn.parking.application.query.handle;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.domain.model.EntryCore;
import co.com.ceiba.adn.parking.domain.query.port.QueryPortEntry;

@Component
public class QueryHandleEntryFindAll {

	private final QueryPortEntry queryPortEntry;
	
	public QueryHandleEntryFindAll(QueryPortEntry queryPortEntry) {
		this.queryPortEntry = queryPortEntry;
	}
	
	public List<EntryCore> handle() {
		return this.queryPortEntry.findAll();
	}
}
