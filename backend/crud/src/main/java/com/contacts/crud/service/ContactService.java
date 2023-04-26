package com.contacts.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.contacts.crud.controller.dto.ContactDTO;
import com.contacts.crud.domain.Contact;
import com.contacts.crud.domain.People;
import com.contacts.crud.repository.ContactRepository;
import com.contacts.crud.repository.PeopleRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PeopleRepository peopleResopitory;
	
	public List<Contact> list() {
		return contactRepository.findAll();
	}
	
	public Contact findId(Integer id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
	}
	
	public Contact save(ContactDTO dto) {

		Integer idPeople = dto.getIdPeople();

		People people = peopleResopitory.findById(idPeople)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa inexistente"));
		
		Contact contact = new Contact();
		contact.setName(dto.getName());
		contact.setPhone(dto.getPhone());
		contact.setEmail(dto.getEmail());
		contact.setPeople(people);

		return contactRepository.save(contact);
	}
	
	public void update(Integer id, ContactDTO dto) {

		Integer idPeople = dto.getIdPeople();
		
		contactRepository.findById(id).map(contact -> {
			contact.setName(dto.getName());
			contact.setPhone(dto.getPhone());
			contact.setEmail(dto.getEmail());
			People people = peopleResopitory.findById(idPeople)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa inexistente"));
			contact.setPeople(people);
			return contactRepository.save(contact);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrada"));	

	}
	
	public void delete(Integer id) {
		contactRepository.findById(id).map(people -> {
			contactRepository.delete(people);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrada"));
	}
	
}
