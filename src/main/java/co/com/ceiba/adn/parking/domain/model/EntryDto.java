package co.com.ceiba.adn.parking.domain.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntryDto {

	private String licencePlate;

	private String vehicleType;
	
	private String entryTime;
	
	public EntryDto(String licencePlate, String vehicleType, Calendar entryTime) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		this.licencePlate = licencePlate;
		this.vehicleType = vehicleType;
		this.entryTime = formatter.format(entryTime.getTime());
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public String getEntryTime() {
		return entryTime;
	}

}
