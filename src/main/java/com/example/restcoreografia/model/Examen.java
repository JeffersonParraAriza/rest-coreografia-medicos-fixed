package com.example.restcoreografia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Genera constructor vacío
@AllArgsConstructor // Genera constructor con todos los argumentos
public class Examen {
    private Long id;
    private Long pacienteId;
    private String tipo;
    private String resultado;
    private String fecha;
}
