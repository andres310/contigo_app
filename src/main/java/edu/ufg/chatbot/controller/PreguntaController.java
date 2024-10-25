package edu.ufg.chatbot.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ufg.chatbot.dto.PromptDTO;
import edu.ufg.chatbot.dto.mapper.IPromptDTOMapper;
import edu.ufg.chatbot.entity.Pregunta;
import edu.ufg.chatbot.repository.IPreguntaRepository;

@Validated
@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {
	
	final IPreguntaRepository repository;
	final IPromptDTOMapper mapper;
	
	public PreguntaController(IPreguntaRepository repository, IPromptDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	// 1 - Devuelve la respuesta guardada en base
	@PostMapping("/respuesta")
	public PromptDTO getRespuesta(@RequestBody PromptDTO prompt) {
		return this.repository.findByPregunta(prompt.pregunta())
				.map(this.mapper::map)
				.orElseGet(() -> new PromptDTO("", "No se encontro la pregunta"));
	}

    // 2 - recibe prompt y devuelve true o falso dependiendo de si esta la respuesta en la base
	@PostMapping("/existe")
	public Boolean existeEnBase(@RequestBody PromptDTO prompt) {
		return this.repository.existsByPregunta(prompt.pregunta());
	}
	
	// 3 - Guardar pregunta y respuesta
	@PostMapping
	public PromptDTO postPreguntaYRespuesta(@RequestBody PromptDTO dto) {

		// Verificar respuesta vacia
		if (dto.respuesta() == null || dto.respuesta().isBlank()) {
			return new PromptDTO(dto.pregunta(), "respuesta vacía");
		}

		// Verificar si la pregunta ya existe
		if (this.repository.existsByPregunta(dto.pregunta())) {
			// Si ya existe, devolver el DTO sin guardar
			return dto;
		}

		// Si no existe, guardar la nueva pregunta y devolver el resultado
		Pregunta entity = this.mapper.map(dto);
		return this.mapper.map(this.repository.save(entity));
	}
}
