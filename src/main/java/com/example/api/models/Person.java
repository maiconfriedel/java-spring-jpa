package com.example.api.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class Person {

	@Schema(description = "Identificador da pessoa")
	public long id;

	@Schema(description = "Nome da pessoa")
	public String name;

	public static com.example.infrastructure.models.Person ToInfra(Person person) {
		var infraPerson = new com.example.infrastructure.models.Person();
		
		infraPerson.setId(person.getId());
		infraPerson.setName(person.getName());

		return infraPerson;
	}
	
	public Person() {
	}

	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
