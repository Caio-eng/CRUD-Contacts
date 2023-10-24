package com.contacts.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.contacts.crud.domain.CEP;
import com.google.gson.Gson;

@Service
public class BrasilAPIService {
	
	private static final String BAR = "<2F>";
	private static Gson gson = new Gson();
	
	public CEP findCep(@PathVariable String cep) {
		CEP obj = (CEP) api(CEP.class, "cep/v1/", cep);
		return obj != null ? (CEP) obj.clone() : null;
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
