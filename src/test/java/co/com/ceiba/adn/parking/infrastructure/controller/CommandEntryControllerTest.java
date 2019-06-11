package co.com.ceiba.adn.parking.infrastructure.command.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.com.ceiba.adn.parking.ApplicationMock;
import co.com.ceiba.adn.parking.application.command.testdatabuilder.CommandEntryTestDataBuilder;
import co.com.ceiba.adn.parking.service.application.command.CommandEntry;
import co.com.ceiba.adn.parking.service.application.command.handle.CommandHandleCreateEntry;
import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;
import co.com.ceiba.adn.parking.service.infrastructure.command.controller.CommandEntryController;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ApplicationMock.class)
@WebMvcTest(CommandEntryController.class)
public class CommandEntryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QueryPortEntry queryPortEntry;

	@MockBean
	private CommandPortEntry commandPortEntry;

	@MockBean
	private CommandHandleCreateEntry commandHandleCreateEntry;

	@Test
	public void createEntry() throws Exception {

		// Arrange
		
		CommandEntryTestDataBuilder commandEntryTestDataBuilder = new CommandEntryTestDataBuilder();
		CommandEntry commandEntry = commandEntryTestDataBuilder.withLicencePlate(null).build();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		String entryJson = ow.writeValueAsString(commandEntry);

		// Act - Assert
		this.mockMvc.perform(post("/entry").contentType(MediaType.APPLICATION_JSON_UTF8).content(entryJson))
				.andDo(print()).andExpect(status().isOk());
	}
}
