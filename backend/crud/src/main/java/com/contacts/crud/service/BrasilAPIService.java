package com.contacts.crud.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.contacts.crud.domain.CEP;
import com.contacts.crud.domain.CNPJ;
import com.google.gson.Gson;

@Service
public class BrasilAPIService {
	
	private static final String BAR = "<2F>";
	private static Gson gson = new Gson();
	
	public CEP findCep(String cep) {
		
		CEP obj = (CEP) api(CEP.class, "cep/v1/", cep);
		
		if ( obj == null ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado");
		}
		
		if ( obj.getService().equals("widenet") ) {
			obj.setService("Correios");
		}
		
		return obj != null ? (CEP) obj.clone() : null;
	}
	
	public CNPJ findCnpj(String cnpj) {
		CNPJ obj = (CNPJ) api(CNPJ.class, "cnpj/v1/", cnpj);
		
		return obj != null ? (CNPJ) obj.clone() : null;
	}
	
	private static Object api(Class<?> classAPIModel, String parameter, String code) {
		try {
			code = code.replaceAll("/", "").replaceAll(BAR, "/");

			String json = ConnectionAPIService.connection(parameter + code);
			return gson.fromJson(json, classAPIModel);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
