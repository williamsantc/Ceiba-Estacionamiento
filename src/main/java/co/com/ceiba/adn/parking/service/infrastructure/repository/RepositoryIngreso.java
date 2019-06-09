package co.com.ceiba.adn.parking.service.infrastructure.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityIngreso;

public interface RepositoryIngreso extends CrudRepository<EntityIngreso, Long>  {
	
	List<EntityIngreso> findAll();
	
	List<EntityIngreso> findByTipoVehiculo(String tipoVehiculo);
}
