package co.com.ceiba.adn.parking.service.infrastructure.command.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortIngreso;
import co.com.ceiba.adn.parking.service.domain.mapper.MapperIngresoImlp;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryIngreso;

@Component
public class CommandAdapterIngreso implements CommandPortIngreso {

	@Autowired
	private RepositoryIngreso repositoryIngreso;
	
	@Autowired
	private MapperIngresoImlp mapperIngresoImlp;

	@Override
	public Ingreso insertIngreso(Ingreso ingreso) {
		return mapperIngresoImlp.mapFromEntity(repositoryIngreso.save(mapperIngresoImlp.mapToEntity(ingreso)));
	}
}
