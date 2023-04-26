package com.contacts.crud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O Nome é obrigatório")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "O Telefone é obrigatório")
	@Column(nullable = false)
	private String phone;
	
	@NotBlank(message = "O Email é obrigatório")
	@Email(message = "Email inválido")
	@Column(nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "people_id")
	private People people;

	public Contact() {
	}

	public Contact(Integer id, @NotBlank(message = "O Nome é obrigatório") String name,
			@NotBlank(message = "O Telefone é obrigatório") String phone,
			@NotBlank(message = "O Email é obrigatório") @Email(message = "Email inválido") String email,
			People people) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.people = people;
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

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
}
