package com.minhojang.jpan1practice.repository;

import com.minhojang.jpan1practice.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
