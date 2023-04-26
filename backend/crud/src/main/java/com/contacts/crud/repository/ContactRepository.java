package com.contacts.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacts.crud.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
