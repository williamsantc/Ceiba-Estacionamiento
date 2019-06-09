package co.com.ceiba.adn.parking.service.domain.model;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingreso {
	
private Long id;
	
	private String placa;
	
	private String tipoVehiculo;
	
	private String cilindraje;
	
	private Calendar registroEntrada;
	
	// implement real constructor
	
	
}
