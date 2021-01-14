package com.example.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByName(String name);
}
