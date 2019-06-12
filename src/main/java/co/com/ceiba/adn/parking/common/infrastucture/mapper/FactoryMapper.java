package co.com.ceiba.adn.parking.common.infrastucture.mapper;

import org.modelmapper.ModelMapper;

public class FactoryMapper extends ModelMapper {
	
	private static final FactoryMapper INSTANCE = new FactoryMapper();
	
	public static FactoryMapper getInstance() {
		return INSTANCE;
	}
}
