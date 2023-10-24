package com.contacts.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.crud.domain.CEP;
import com.google.gson.Gson;

@RestController
@RequestMapping("/endereco")
public class BrasilAPI {
	
	private static final String BAR = "<2F>";
	private static Gson gson = new Gson();
	
	@GetMapping(value = "/{cep}")
	public CEP findCep(@PathVariable String cep) {
		CEP obj = (CEP) api(CEP.class, "cep/v1/", cep);
		return obj != null ? (CEP) obj.clone() : null;
	}
	
	private static Object api(Class<?> classAPIModel, String parameter, String code) {
		try {
			code = code.replaceAll("/", "").replaceAll(BAR, "/");
			if (Cache.getEnableCache()) {
				Object obj = Cache.getCache(classAPIModel, code);
	
				if (obj == null) {
					String json = Service.connection(parameter + code);
					if (json != null) {
						obj = gson.fromJson(json, classAPIModel);
						Cache.setCache(classAPIModel, code, obj);
					}
				}
	
				return obj;
			} else {
				String json = Service.connection(parameter + code);
				return gson.fromJson(json, classAPIModel);
			}
		} catch (Exception e) {
			Log.setConsoleError(e.getMessage());
			return null;
		}
	}

}
