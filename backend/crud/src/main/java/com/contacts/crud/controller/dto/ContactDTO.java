package com.contacts.crud.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContactDTO {

	@NotBlank(message = "O Nome é obrigatório")
	private String name;
	
	@NotBlank(message = "O Telefone é obrigatório")
	private String phone;
	
	@NotBlank(message = "O Email é obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotNull(message = "O campo Pessoa é obrigatório")
	private Integer idPeople;

	public ContactDTO() {
	}

	public ContactDTO(@NotBlank(message = "O Nome é obrigatório") String name,
			@NotBlank(message = "O Telefone é obrigatório") String phone,
			@NotBlank(message = "O Email é obrigatório") @Email(message = "Email inválido") String email,
			@NotNull(message = "O campo Pessoa é obrigatório") Integer idPeople) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.idPeople = idPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(Integer idPeople) {
		this.idPeople = idPeople;
	}
	
}
