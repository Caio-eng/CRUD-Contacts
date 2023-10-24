package com.contacts.crud.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.contacts.crud.domain.People;
import com.contacts.crud.repository.PeopleRepository;

@Service
public class PeopleService {

	@Autowired
	private PeopleRepository resopitory;
	
	public Page<People> list(Integer page, Integer sizePage) {
		PageRequest pageRequest = PageRequest.of(page, sizePage);
		return resopitory.findAll(pageRequest);
	}
	
	public List<People> listAll() {
		return resopitory.findAll();
	}
	
	public People findId(Integer id) {
		return resopitory.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}
	
	public People save(People people) {
		LocalDate dateNow = LocalDate.now();
		
		if ( people.getDateBirth().isAfter(dateNow) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento não pode ser maior que a data atual");
		}
		
		if (people.getCpf().length() > 11) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF tem que ter menos de 11 caracteres");
		}
		return resopitory.save(people);
	}
	
	public void update(Integer id, People updatePeople) {
		resopitory.findById(id).map(people -> {
			people.setName(updatePeople.getName());
			people.setCpf(updatePeople.getCpf());
			people.setDateBirth(updatePeople.getDateBirth());
			people.setCEP(updatePeople.getCEP());
			LocalDate dateNow = LocalDate.now();
			
			if ( people.getDateBirth().isAfter(dateNow) ) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento não pode ser maior que a data atual");
			}
			
			if (people.getCpf().length() > 11) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF tem que ter menos de 11 caracteres");
			}
			
			return resopitory.save(people);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}
	
	public void delete(Integer id) {
		resopitory.findById(id).map(people -> {
			resopitory.delete(people);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}
	
}
