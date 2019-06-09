package co.com.ceiba.adn.parking.service.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.parking.common.domain.mapper.FactoryMapper;
import co.com.ceiba.adn.parking.service.domain.model.Ingreso;
import co.com.ceiba.adn.parking.service.infrastructure.entity.EntityIngreso;

@Component
public class MapperIngresoImlp implements MapperIngreso {
	
	@Override
	public EntityIngreso mapToEntity(Ingreso ingreso) {
		return FactoryMapper.getInstance().map(ingreso, EntityIngreso.class);
	}

	@Override
	public List<Ingreso> mapFromEntityList(List<EntityIngreso> listIngreso) {
		
		List<Ingreso> lista = new ArrayList<>();
		listIngreso.forEach(entityIngreso -> {
			lista.add(FactoryMapper.getInstance().map(entityIngreso, Ingreso.class));
		});
		return lista;
	}
	
	@Override
	public Ingreso mapFromEntity(EntityIngreso entityIngreso) {
		return FactoryMapper.getInstance().map(entityIngreso, Ingreso.class);
	}

}
