package edu.ufg.chatbot.repository;

import edu.ufg.chatbot.entity.Pregunta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Integer> {
	
	boolean existsByPregunta(String pregunta);
	
	Optional<Pregunta> findByPregunta(String pregunta);
}
