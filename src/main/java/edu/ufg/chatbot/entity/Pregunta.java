package edu.ufg.chatbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pregunta")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pregunta {

    @Id
    @Column(name = "id_pregunta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPregunta;
    @Lob
    String pregunta;

    @Lob
    String respuesta;

    String region;

    String ciudad;
}
