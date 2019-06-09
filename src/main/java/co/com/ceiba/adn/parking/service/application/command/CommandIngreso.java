package co.com.ceiba.adn.parking.service.application.command;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandIngreso {

private Long id;
	
	private String placa;
	
	private String tipoVehiculo;
	
	private String cilindraje;
	
	private Calendar registroEntrada;
}
