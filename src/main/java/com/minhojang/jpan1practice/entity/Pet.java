package com.minhojang.jpan1practice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String classification;

	@ManyToOne
	@JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "FK_PET_PERSON"))
	Person person;

	@Builder
	public Pet(String name, String classification) {
		this.name = name;
		this.classification = classification;
	}

	void updatePerson(Person person) {
		this.person = person;
	}
}
