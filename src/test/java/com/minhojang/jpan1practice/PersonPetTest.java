package com.minhojang.jpan1practice;

import com.minhojang.jpan1practice.entity.Person;
import com.minhojang.jpan1practice.entity.Pet;
import com.minhojang.jpan1practice.repository.PersonRepository;
import com.minhojang.jpan1practice.repository.PetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class PersonPetTest {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PetRepository petRepository;

	@BeforeEach
	void setup() {
		for (int i=0; i<10; i++) {
			Pet pet = Pet.builder()
					.name("뽀삐" + i)
					.classification("개")
					.build();
			Pet pet2 = Pet.builder()
					.name("나비" + i)
					.classification("고양이")
					.build();

			Person person = Person.builder()
					.name("홍길동" + i)
					.age(21)
					.build();
			person.addPet(pet);
			person.addPet(pet2);
			personRepository.save(person);
		}

		System.out.println("============== TEST ============== TEST ============== TEST ============== TEST ==============");
	}

	@AfterEach
	void deleteAll() {
		System.out.println("============== TEST ============== TEST ============== TEST ============== TEST ==============");
		personRepository.deleteAll();
	}

	@Test
	void test1() {
		List<Person> personList = personRepository.findAll();
		assertThat(personList.size(), is(10));
	}

	@Test
	@Transactional(readOnly = true)
	void test2() {
		List<Person> personList = personRepository.findAll();
		List<String> petNameList = personList.stream()
				.map(p -> p.getPets().get(0).getName())
				.collect(Collectors.toList());

		assertThat(petNameList.size(), is(10));
	}

	@Test
	@Transactional(readOnly = true)
	void test3() {
		List<Person> personList = personRepository.findAllJoinFetch();
		List<String> petNameList = personList.stream()
				.map(p -> p.getPets().get(0).getName())
				.collect(Collectors.toList());

		assertThat(petNameList.size(), is(10));
	}

	@Test
	@Transactional(readOnly = true)
	void test4() {
		List<Person> personList = personRepository.findAllEntityGraph();
		List<String> petNameList = personList.stream()
				.map(p -> p.getPets().get(0).getName() + p.getPets().get(1).getName())
				.collect(Collectors.toList());

		assertThat(petNameList.size(), is(10));
	}
}
