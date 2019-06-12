package co.com.ceiba.adn.parking.infrastructure.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Entry")
public class EntityEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    private Long id;
	
	@Column
	private String licencePlate;
	
	@Column
	private String vehicleType;
	
	@Column
	private String engineDisplacement;
	
	@Column
	private Calendar entryTime;
	
}
