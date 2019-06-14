package co.com.ceiba.adn.parking.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.com.ceiba.adn.ApplicationMock;
import co.com.ceiba.adn.ParkingApplication;
import co.com.ceiba.adn.parking.application.command.CommandEntry;
import co.com.ceiba.adn.parking.application.command.testdatabuilder.CommandEntryTestDataBuilder;

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
		this.mockMvc.perform(get("/entry")).andDo(print()).andExpect(status().isAccepted())
				// Assert
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	@Test
	public void dispatchEntry() throws Exception {

		// Arrange

		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		CommandEntry commandEntry = commandEntryTestDataBuilder.withId(1L).build();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		String entryJson = ow.writeValueAsString(commandEntry);

		// Act
		this.mockMvc.perform(post("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson));
		this.mockMvc.perform(put("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson))
				// Assert
				.andExpect(status().isOk()).andExpect(content().string("{\"value\":1000.0}"));
	}

	@Test
	public void createEntry() throws Exception {

		// Arrange

		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		CommandEntry commandEntry = commandEntryTestDataBuilder.build();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		String entryJson = ow.writeValueAsString(commandEntry);

		// Act
		this.mockMvc.perform(post("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson))
				.andDo(print())
				// Assert
				.andExpect(status().isOk()).andExpect(content().string("{\"value\":2}"));
	}

	
}
