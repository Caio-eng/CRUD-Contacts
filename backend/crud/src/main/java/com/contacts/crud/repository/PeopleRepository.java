package com.contacts.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacts.crud.domain.People;

public interface PeopleRepository extends JpaRepository<People, Integer> {

}
