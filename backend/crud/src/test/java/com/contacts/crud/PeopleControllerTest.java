package com.contacts.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.contacts.crud.controller.PeopleController;
import com.contacts.crud.domain.People;
import com.contacts.crud.service.PeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PeopleController.class)
class PeopleControllerTest {

	@MockBean
	private PeopleService peopleService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void givenValidId_whenGetPeople_thenReturnsPeople() throws Exception {
		People people = new People();
		people.setId(1);
		people.setName("Teste Cliente Unitario");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		when(peopleService.findId(1)).thenReturn(people);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(people);

		MvcResult mvcResult = mockMvc.perform(get("/people/{id}", 1)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(peopleJson, content);
	}

	@Test
	void givenValidPeople_whenCreatePeople_thenReturnsCreatedStatus() throws Exception {
		People people = new People();
		people.setId(1);
		people.setName("Teste Cliente Unitario");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		when(peopleService.save(people)).thenReturn(people);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(people);

		MvcResult mvcResult = mockMvc.perform(post("/people").contentType("application/json").content(peopleJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	void givenValidIdAndPeople_whenUpdatePeople_thenReturnsNoContentStatus() throws Exception {
		People people = new People();
		people.setId(1);
		people.setName("Teste Cliente Unitario");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		doNothing().when(peopleService).update(1, people);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(people);
		MvcResult mvcResult = mockMvc
				.perform(put("/people/{id}", 1).contentType("application/json").content(peopleJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}
	
	@Test
	void givenValidIdAndPeople_whenDeletePeople_thenReturnsNoContentStatus() throws Exception {
		People people = new People();
		people.setId(1);
		people.setName("Teste Cliente Unitario");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		doNothing().when(peopleService).delete(1);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(people);
		MvcResult mvcResult = mockMvc
				.perform(delete("/people/{id}", 1).contentType("application/json").content(peopleJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}

}
