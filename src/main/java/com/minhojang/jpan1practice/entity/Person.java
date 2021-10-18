package com.minhojang.jpan1practice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int age;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private List<Pet> pets = new ArrayList<>();

	@Builder
	public Person(String name, int age, List<Pet> pets) {
		this.name = name;
		this.age = age;
		if (pets != null) {
			this.pets = pets;
		}
	}

	public void addPet(Pet pet) {
		this.pets.add(pet);
		pet.updatePerson(this);
	}
}
