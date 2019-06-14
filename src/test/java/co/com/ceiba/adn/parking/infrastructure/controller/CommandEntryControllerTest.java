package co.com.ceiba.adn.parking.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import co.com.ceiba.adn.ApplicationMock;
import co.com.ceiba.adn.ParkingApplication;
import co.com.ceiba.adn.common.application.CommandResponse;
import co.com.ceiba.adn.common.infrastructure.error.Error;
import co.com.ceiba.adn.parking.application.command.CommandEntry;
import co.com.ceiba.adn.parking.application.command.testdatabuilder.CommandEntryTestDataBuilder;
import co.com.ceiba.adn.parking.domain.exception.ExceptionEntryNotFound;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@SpringBootTest(classes = ParkingApplication.class)
public class CommandEntryControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void checkList() throws Exception {

		// Act
		this.mockMvc.perform(get("/entry")).andExpect(status().isAccepted())
				// Assert
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void createEntry() throws Exception {

		// Arrange

		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		CommandEntry commandEntry = commandEntryTestDataBuilder.build();
		JSONObject entryJson = new JSONObject(commandEntry);
		
		CommandResponse<Long> response = new CommandResponse<>(1L);
		JSONObject responseJson = new JSONObject(response);

		// Act
		this.mockMvc.perform(post("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson.toString()))
				.andDo(print())
				// Assert
				.andExpect(status().isOk()).andExpect(content().json(responseJson.toString()));
	}

	@Test
	public void dispatchEntry() throws Exception {

		// Arrange
		String storedLicencePlate = "ER1243";
		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		
		CommandEntry commandEntry = commandEntryTestDataBuilder.withLicencePlate(storedLicencePlate).build();
		JSONObject entryJson = new JSONObject(commandEntry);
		
		CommandResponse<Double> response = new CommandResponse<>(1320000D);
		JSONObject responseJson = new JSONObject(response);
		
		// Act
		this.mockMvc.perform(put("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson.toString()))
				// Assert
				.andExpect(status().isOk()).andExpect(content().json(responseJson.toString()));
	}

	@Test
	public void dispatchEntryFail() throws Exception {

		// Arrange
		String storedLicencePlate = "ER1243";
		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		CommandEntry commandEntry = commandEntryTestDataBuilder.withLicencePlate(storedLicencePlate).build();

		String exceptionName = ExceptionEntryNotFound.class.getSimpleName();
		String message = "No se encontró ningún vehiculo en el parqueadero con la placa proporcionada";
		Error error = new Error(exceptionName, message);

		JSONObject errorJson = new JSONObject(error);
		JSONObject entryJson = new JSONObject(commandEntry);
		
		// Act
		this.mockMvc.perform(put("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson.toString()))
				// Assert
				.andExpect(status().isNotFound()).andExpect(content().json(errorJson.toString()));
	}

}
