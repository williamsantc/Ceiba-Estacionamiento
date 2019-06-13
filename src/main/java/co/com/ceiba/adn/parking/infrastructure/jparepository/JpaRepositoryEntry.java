package co.com.ceiba.adn.parking.infrastructure.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.adn.parking.infrastructure.entity.EntityEntry;

public interface JpaRepositoryEntry extends CrudRepository<EntityEntry, Long>  {
	
	List<EntityEntry> findAll();
	
	List<EntityEntry> findByVehicleType(String vehicleType);
	
	@Query("select count(*) from EntityEntry i where i.vehicleType = :vehicleType")
	int countByVehicleType(@Param("vehicleType") String vehicleType);
	
	@Query("select i from EntityEntry i where i.licencePlate = :licencePlate")
	EntityEntry findByLicencePlate(@Param("licencePlate") String licencePlate);
}
