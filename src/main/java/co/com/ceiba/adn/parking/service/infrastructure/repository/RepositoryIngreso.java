package co.com.ceiba.adn.parking.service.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityIngreso;

public interface RepositoryIngreso extends CrudRepository<EntityIngreso, Long>  {
	
	List<EntityIngreso> findAll();
	
	List<EntityIngreso> findByTipoVehiculo(String tipoVehiculo);
	
	@Query("select count(*) from EntityIngreso i where i.tipoVehiculo = :tipoVehiculo")
	int countByTipoVehiculo(@Param("tipoVehiculo") String tipoVehiculo);
}
