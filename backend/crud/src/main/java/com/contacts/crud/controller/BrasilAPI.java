package com.contacts.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.crud.domain.CEP;
import com.contacts.crud.service.BrasilAPIService;

@RestController
@RequestMapping("/endereco")
public class BrasilAPI {
	
	@Autowired
	private BrasilAPIService service;
	
	@GetMapping(value = "/{cep}")
	public CEP findCep(@PathVariable String cep) {
		return service.findCep(cep);
	}


}
