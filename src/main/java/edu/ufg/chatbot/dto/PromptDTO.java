package edu.ufg.chatbot.dto;

import jakarta.validation.constraints.NotBlank;

public record PromptDTO(
		@NotBlank(message = "El prompt no puede estar vacio.")
		String pregunta,
		String respuesta
	) {

}
