package com.example.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.Error;
import com.example.api.models.Person;
import com.example.infrastructure.repositories.PersonRepository;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController()
@RequestMapping("/persons")
@Tag(name = "Persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Operation(summary = "Listar pessoas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de pessoas", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "500", description = "Erro Interno", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) }) })
	@GetMapping
	public List<Person> get() {
		var personsInfra = personRepository.findAll();

		ArrayList<Person> persons = new ArrayList<Person>();

		for (com.example.infrastructure.models.Person person : personsInfra) {
			persons.add(com.example.infrastructure.models.Person.ToDomain(person));
		}

		return persons;
	}

	@Operation(summary = "Criar pessoa")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pessoa criada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "500", description = "Erro Interno", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) }) })
	@PostMapping
	public Object post(@RequestBody Person person, HttpServletResponse response) {
		com.example.infrastructure.models.Person existing = personRepository.findByName(person.getName());

		if (existing != null) {
			return com.example.infrastructure.models.Person.ToDomain(existing);
		}
		try {

			com.example.infrastructure.models.Person toCreatePerson = Person.ToInfra(person);

			var createdPerson = personRepository.save(toCreatePerson);

			return com.example.infrastructure.models.Person.ToDomain(createdPerson);

		} catch (Exception ex) {

			ArrayList<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());
			response.setStatus(500);

			return new Error(ex.getMessage(), errors);
		}
	}
}