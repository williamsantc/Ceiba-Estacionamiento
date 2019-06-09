package co.com.ceiba.adn.parking.service.domain.mapper;

import java.util.List;

import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityIngreso;

public interface MapperIngreso {
	
	EntityIngreso mapToEntity (Ingreso ingreso);
	
	Ingreso mapFromEntity (EntityIngreso entityIngreso);
	
	List<Ingreso> mapFromEntityList (List<EntityIngreso> listIngreso);
}
