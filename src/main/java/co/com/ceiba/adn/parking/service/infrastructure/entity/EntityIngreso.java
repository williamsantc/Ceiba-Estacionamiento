package co.com.ceiba.adn.parking.service.infrastructure.entity;

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
@Table(name="Ingreso")
public class EntityIngreso {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    private Long id;
	
	@Column
	private String placa;
	
	@Column
	private String tipoVehiculo;
	
	@Column
	private String cilindraje;
	
	@Column
	private Calendar registroEntrada;
	
}
