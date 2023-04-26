package com.contacts.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contacts.crud.domain.Contact;
import com.contacts.crud.domain.People;
import com.contacts.crud.repository.ContactRepository;
import com.contacts.crud.repository.PeopleRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ContactRepositoryTest {
	
	@Autowired
    private PeopleRepository peopleResopitory;
	
	@Autowired
	private ContactRepository contactRepository;
	
	People people;

	@BeforeEach
    public void setUp() {
		people = new People();
		people.setName("Teste Cliente Unitário");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(people);
    }
	
	@Test
	public void insertContact() {
		
		Contact contact = new Contact();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		contactRepository.save(contact);
		
		Integer countInsertContact = contactRepository.findAll().size();
		assertEquals(1, countInsertContact);
		
	}
	
	@Test
    public void checkContactById() {
		Contact contact = new Contact();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		contactRepository.save(contact);
		
		Integer countInsertContact = contactRepository.findAll().size();
		assertEquals(1, countInsertContact);
		
        Optional<Contact> insertedContact = contactRepository.findById(contact.getId());

        assertNotNull(insertedContact);
        assertEquals(contact, insertedContact.get());
    }
	
	@Test
	public void deleteContact() {
		Contact contact = new Contact();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		contactRepository.save(contact);
		
		Integer countInsertContact = contactRepository.findAll().size();
		assertEquals(1, countInsertContact);
		
        contactRepository.delete(contact);

        Integer countDeleteContact = contactRepository.findAll().size();
		assertEquals(0, countDeleteContact);
	}
	
	@Test
    public void updateContact() {
		Contact contact = new Contact();
		contact.setName("Testado");
		contact.setPhone("62998654125");
		contact.setEmail("testado@hotmail.com");
		contact.setPeople(people);
		
		contactRepository.save(contact);
		
		Contact updateContact = new Contact();
		updateContact.setId(contact.getId());
		updateContact.setName("Testado Atualizado");
		updateContact.setPhone("62998654125");
		updateContact.setEmail("testado@hotmail.com");
		updateContact.setPeople(people);
		
		contactRepository.save(updateContact);
		
		
		assertEquals(contact.getName(), updateContact.getName());
		assertEquals(contact.getPhone(), updateContact.getPhone());
    }
	
}
