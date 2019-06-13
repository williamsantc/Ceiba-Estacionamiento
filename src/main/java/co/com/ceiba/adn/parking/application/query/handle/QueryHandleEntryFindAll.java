package co.com.ceiba.adn.parking.application.query.handle;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.domain.model.EntryDto;
import co.com.ceiba.adn.parking.domain.query.repository.QueryRepositoryEntry;

@Component
public class QueryHandleEntryFindAll {

	private final QueryRepositoryEntry queryRepositoryEntry;
	
	public QueryHandleEntryFindAll(QueryRepositoryEntry queryRepositoryEntry) {
		this.queryRepositoryEntry = queryRepositoryEntry;
	}
	
	public List<EntryDto> handle() {
		return this.queryRepositoryEntry.findAll();
	}
}
