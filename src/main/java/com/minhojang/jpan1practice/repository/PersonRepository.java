package com.minhojang.jpan1practice.repository;

import com.minhojang.jpan1practice.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p join fetch p.pets")
	List<Person> findAllJoinFetch();

	@EntityGraph(attributePaths = "pets")
	@Query("select p from Person p")
	List<Person> findAllEntityGraph();
}
