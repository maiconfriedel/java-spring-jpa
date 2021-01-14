package com.example.api.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class Error {

	@Schema(description = "Mensagem amigável")
	public final String message;

	@Schema(description = "Lista de erros")
	public final List<String> errors;

	public Error(String message, List<String> errors) {
		this.message = message;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

}
