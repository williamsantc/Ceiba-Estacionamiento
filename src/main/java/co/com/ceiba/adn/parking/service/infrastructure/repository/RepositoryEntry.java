package co.com.ceiba.adn.parking.service.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityEntry;

public interface RepositoryEntry extends CrudRepository<EntityEntry, Long>  {
	
	List<EntityEntry> findAll();
	
	List<EntityEntry> findByVehicleType(String vehicleType);
	
	@Query("select count(*) from EntityEntry i where i.vehicleType = :vehicleType")
	int countByVehicleType(@Param("vehicleType") String vehicleType);
}
