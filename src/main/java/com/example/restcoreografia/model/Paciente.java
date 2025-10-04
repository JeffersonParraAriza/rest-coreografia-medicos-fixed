package com.example.restcoreografia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Genera constructor vacío
@AllArgsConstructor // Genera constructor con todos los argumentos
public class Paciente {
    private Long id;
    private String nombre;
    private String documento;
    private String email;
}
