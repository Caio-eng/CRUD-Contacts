package com.contacts.crud.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "O campo Nome é obrigatório")
	private String name;
	
	@CPF(message = "CPF está inválido")
	@NotNull(message = "O campo CPF é obrigatório")
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@NotNull(message = "O campo Data de Nascimento é obrigatório")
	@Past
	@Column(name = "date_birth", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateBirth;
	
	private String CEP;
	
	@OneToMany(mappedBy = "people", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Contact> contacts = new ArrayList<>();

	public People() {
	}

	public People(Integer id, @NotEmpty(message = "O campo Nome é obrigatório") String name,
			@CPF(message = "CPF está inválido") @NotNull(message = "O campo CPF é obrigatório") String cpf,
			@NotNull(message = "O campo Data de Nascimento é obrigatório") @Past LocalDate dateBirth, String cep) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
		this.CEP = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
}
