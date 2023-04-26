package com.contacts.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.crud.domain.People;
import com.contacts.crud.service.PeopleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody Page<People> list(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer sizePage
			) {
		return service.list(page, sizePage);
	}

	@GetMapping(value = "/{id}")
	public People findId(@PathVariable Integer id) {
		return service.findId(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public People save(@RequestBody @Valid People people) {
		return service.save(people);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid People updatePeople) {
		service.update(id, updatePeople);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
