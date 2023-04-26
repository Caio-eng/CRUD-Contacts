package com.contacts.crud;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contacts.crud.domain.People;
import com.contacts.crud.repository.PeopleRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PeopleRepositoryTests {

	@Autowired
    private PeopleRepository peopleResopitory;

	@Test
	public void insertUser() {
		People people = new People();
		people.setName("Teste Cliente Unitário");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(people);
		
		Integer countInsertPeople = peopleResopitory.findAll().size();
		assertEquals(1, countInsertPeople);
	}
	
	@Test
    public void checkPeopleById() {
		People people = new People();
		people.setName("Teste Cliente Unitário");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(people);
		
		Integer countInsertPeople = peopleResopitory.findAll().size();
		assertEquals(1, countInsertPeople);
		
        Optional<People> insertedPeople = peopleResopitory.findById(people.getId());

        assertNotNull(insertedPeople);
        assertEquals(people, insertedPeople.get());
    }
	
	@Test
	public void deletePeople() {
		People people = new People();
		people.setName("Teste Cliente Unitário");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(people);
		
		Integer countInsertPeople = peopleResopitory.findAll().size();
		assertEquals(1, countInsertPeople);
		
        peopleResopitory.delete(people);

        Integer countDeletePeople = peopleResopitory.findAll().size();
		assertEquals(0, countDeletePeople);
	}
	
	@Test
    public void updatePeople() {
		People people = new People();
		people.setName("Teste Cliente Unitário");
		people.setCpf("07747264070");
		people.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(people);
		
		People updatePeople = new People();
		
		updatePeople.setId(people.getId());
		updatePeople.setName("Teste Cliente Unitário Atualizado");
		updatePeople.setCpf("07747264070");
		updatePeople.setDateBirth(LocalDate.parse("2020-04-25", DateTimeFormatter.ISO_DATE));
		
		peopleResopitory.save(updatePeople);
		
		assertEquals(people.getName(), updatePeople.getName());
    }

}
