package co.com.ceiba.adn.parking.infrastructure.query.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.adn.parking.ApplicationMock;
import co.com.ceiba.adn.parking.service.application.query.handle.QueryHandleEntryFindAll;
import co.com.ceiba.adn.parking.service.domain.command.port.CommandPortEntry;
import co.com.ceiba.adn.parking.service.domain.query.port.QueryPortEntry;
import co.com.ceiba.adn.parking.service.infrastructure.command.controller.CommandEntryController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(CommandEntryController.class)
public class QueryEntryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QueryPortEntry queryPortEntry;
	
	@MockBean
	private CommandPortEntry commandPortEntry;

	@MockBean
	private QueryHandleEntryFindAll queryHandleEntryFindAll;

	@Test
	public void checkList() throws Exception {

		// Arrange

		// Act - Assert
		this.mockMvc.perform(get("/entry")).andDo(print()).andExpect(status().isAccepted())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
