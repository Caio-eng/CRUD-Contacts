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

import com.contacts.crud.controller.ContactController;
import com.contacts.crud.controller.dto.ContactDTO;
import com.contacts.crud.domain.Contact;
import com.contacts.crud.domain.People;
import com.contacts.crud.service.ContactService;
import com.contacts.crud.service.PeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {
	
	@MockBean
	private PeopleService peopleService;
	
	@MockBean
	private ContactService contactService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	People people;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		people = new People();
		people.setId(1);
		people.setName("Teste Cliente Unitario");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));

	}
	
	@Test
	void givenValidId_whenGetPeople_thenReturnsContact() throws Exception {		
		Contact contact = new Contact();
		contact.setId(1);
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		when(contactService.findId(1)).thenReturn(contact);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(contact);

		MvcResult mvcResult = mockMvc.perform(get("/contact/{id}", 1)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(peopleJson, content);
	}
	
	@Test
	void givenValidPeople_whenCreateContact_thenReturnsCreatedStatus() throws Exception {
				
		ContactDTO contact = new ContactDTO();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setIdPeople(people.getId());
		
		Contact newContact = new Contact();
		newContact.setName("Testado");
		newContact.setPhone("62998654125");
		newContact.setEmail("testado@hotmail.com");
		newContact.setPeople(people);
		
		when(contactService.save(contact)).thenReturn(newContact);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String contactJson = objectMapper.writeValueAsString(contact);

		MvcResult mvcResult = mockMvc.perform(post("/contact").contentType("application/json").content(contactJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void givenValidIdAndPeople_whenUpdateContact_thenReturnsNoContentStatus() throws Exception {
		ContactDTO contact = new ContactDTO();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setIdPeople(people.getId());
		doNothing().when(contactService).update(1, contact);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String peopleJson = objectMapper.writeValueAsString(contact);
		MvcResult mvcResult = mockMvc
				.perform(put("/contact/{id}", 1).contentType("application/json").content(peopleJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}
	
	@Test
	void givenValidIdAndPeople_whenDeleteContact_thenReturnsNoContentStatus() throws Exception {
		
		ContactDTO contact = new ContactDTO();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setIdPeople(people.getId());
		
		doNothing().when(contactService).delete(1);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String contactJson = objectMapper.writeValueAsString(contact);
		MvcResult mvcResult = mockMvc
				.perform(delete("/contact/{id}", 1).contentType("application/json").content(contactJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
	}

}
