package com.contacts.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.crud.controller.dto.CepDTO;
import com.contacts.crud.domain.CEP;
import com.contacts.crud.domain.CNPJ;
import com.contacts.crud.domain.People;
import com.contacts.crud.service.BrasilAPIService;
import com.contacts.crud.service.PeopleService;

@RestController
@RequestMapping("/endereco")
public class BrasilAPI {
	
	@Autowired
	private BrasilAPIService service;
	
	@Autowired
	private PeopleService peopleService;
	
	@GetMapping(value = "/find/{cep}")
	public CEP findCep(@PathVariable String cep) {
		return service.findCep(cep);
	}
	
	@GetMapping(value = "/emp/{cnpj}")
	public CNPJ findCnpj(@PathVariable String cnpj) {
		return service.findCnpj(cnpj);
	}
	
	@GetMapping(value = "/{id}")
	public CepDTO findCepAndId(@PathVariable Integer id) {
		People people = peopleService.findId(id);
	    CEP endereco = service.findCep(people.getCEP());

	    return new CepDTO(people, endereco);
	}


}
