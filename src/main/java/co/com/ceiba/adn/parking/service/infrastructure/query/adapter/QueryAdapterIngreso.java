package co.com.ceiba.adn.parking.service.infrastructure.query.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.service.domain.mapper.MapperIngresoImlp;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortIngreso;
import co.com.ceiba.adn.parking.service.infrastructure.repository.RepositoryIngreso;

@Component
public class QueryAdapterIngreso implements QueryPortIngreso {
	
	@Autowired
	private RepositoryIngreso repositoryIngreso;
	
	@Autowired
	private MapperIngresoImlp mapperIngresoImlp;

	@Override
	public List<Ingreso> findAll() {
		return this.mapperIngresoImlp.mapFromEntityList(repositoryIngreso.findAll());
	}

	@Override
	public int countByTipoVehiculo(String tipoVehiculo) {
		return this.repositoryIngreso.countByTipoVehiculo(tipoVehiculo);
	}

}
