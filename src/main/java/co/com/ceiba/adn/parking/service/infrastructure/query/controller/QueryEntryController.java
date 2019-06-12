package co.com.ceiba.adn.parking.service.infrastructure.query.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ceiba.adn.parking.service.application.query.handle.QueryHandleEntryFindAll;
import co.com.ceiba.adn.parking.service.domain.model.Entry;

@RestControllerAdvice
@RestController
@RequestMapping("/entry")
@CrossOrigin("*")
public class QueryEntryController {

	private final QueryHandleEntryFindAll queryHandleEntryFindAll;

	@Autowired
	public QueryEntryController(QueryHandleEntryFindAll queryHandleEntryFindAll) {
		this.queryHandleEntryFindAll = queryHandleEntryFindAll;
	}
		
	@GetMapping
	public ResponseEntity<List<Entry>> selectAll() {
		return new ResponseEntity<>( queryHandleEntryFindAll.handle() , HttpStatus.ACCEPTED);
	}
}
