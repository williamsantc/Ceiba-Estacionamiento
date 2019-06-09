package co.com.ceiba.adn.parking.common.domain.mapper;

import org.modelmapper.ModelMapper;

public class FactoryMapper extends ModelMapper {
	
	private static final FactoryMapper INSTANCE = new FactoryMapper();
	
	public static ModelMapper getInstance() {
		return INSTANCE;
	}
}
