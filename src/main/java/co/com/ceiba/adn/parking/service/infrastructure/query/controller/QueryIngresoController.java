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

import co.com.ceiba.adn.parking.service.application.query.handle.QueryHandleIngresoFindAll;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;

@RestControllerAdvice
@RestController
@RequestMapping("/ingreso")
@CrossOrigin("*")
public class QueryIngresoController {

	@Autowired
	private QueryHandleIngresoFindAll queryHandleIngresoFindAll;
		
	@GetMapping
	public ResponseEntity<List<Ingreso>> selectAll() {
		return new ResponseEntity<>( queryHandleIngresoFindAll.handle() , HttpStatus.ACCEPTED);
	}
}
