package com.contacts.crud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.contacts.crud.controller.dto.ContactDTO;
import com.contacts.crud.domain.Contact;
import com.contacts.crud.domain.People;
import com.contacts.crud.repository.ContactRepository;
import com.contacts.crud.repository.PeopleRepository;

@SpringBootTest
class CrudApplicationTests {

	@Autowired
    private PeopleRepository peopleResopitory;
	
	@Autowired
	private ContactRepository contactRepository;
	

	/*
	 * ATENÇÃO: Os Testes foram realizados pelo JUnit
	 */	
	@Test
	public void testContact() {
		
		//INSERT
		
		ContactDTO dto = new ContactDTO();
		dto.setIdPeople(3);
		Integer idPeople = dto.getIdPeople();

		People people = peopleResopitory.findById(idPeople)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa inexistente"));
		
		System.out.println(people.getName());
		
		Contact contact = new Contact();
		
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		contactRepository.save(contact);
		
		System.out.println("Contato: " + contact.getName() + " Telefone: " + contact.getPhone());
		
		//UPDATE
		
		idPeople = 1;
		
		People newPeople = peopleResopitory.findById(idPeople)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa inexistente"));
		
		Contact updateContact = new Contact();
		updateContact.setId(contact.getId());
		updateContact.setName("Testado Atualizado");
		updateContact.setPhone("62998654125");
		updateContact.setEmail("testado@hotmail.com");
		updateContact.setPeople(newPeople);
		
		contactRepository.save(updateContact);
		
		System.out.println("Contato Atualizado: " + updateContact.getName() + " Telefone: " + updateContact.getPhone());
		
		//DELETE
		
		contactRepository.delete(updateContact);
		
		System.out.println("Contato " + updateContact.getName() + " deletado com sucesso!");
		
	}

}
