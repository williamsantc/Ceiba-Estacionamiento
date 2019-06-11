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
public class CommandEntry {
	
	private String licencePlate;

	private String vehicleType;

	private String engineDisplacement;

	private Calendar entryTime;
}
