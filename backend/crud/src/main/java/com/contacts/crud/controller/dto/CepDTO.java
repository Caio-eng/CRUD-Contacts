package com.contacts.crud.controller.dto;

import com.contacts.crud.domain.CEP;
import com.contacts.crud.domain.People;

public class CepDTO {

	private People people;
	private CEP cep;

    public CepDTO(People people, CEP cep) {
    	this.people = people;
        this.cep = cep;
    }

    public People getPeople() {
        return people;
    }
    
    public CEP getCep() {
        return cep;
    }
    
}
