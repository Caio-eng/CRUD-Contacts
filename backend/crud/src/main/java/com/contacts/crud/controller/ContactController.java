package com.contacts.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.crud.controller.dto.ContactDTO;
import com.contacts.crud.domain.Contact;
import com.contacts.crud.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<Contact> list() {
		return service.list();
	}

	@GetMapping(value = "/{id}")
	public Contact findId(@PathVariable Integer id) {
		return service.findId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact save(@RequestBody @Valid ContactDTO dto) {
		return service.save(dto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid ContactDTO dto) {
		service.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
