package edu.ufg.chatbot.dto.mapper;

import org.mapstruct.Mapper;

import edu.ufg.chatbot.dto.PromptDTO;
import edu.ufg.chatbot.entity.Pregunta;

@Mapper(componentModel = "spring")
public interface IPromptDTOMapper {

	PromptDTO map(String prompt);
	Pregunta map(PromptDTO prompt);
	PromptDTO map(Pregunta entity);
}
