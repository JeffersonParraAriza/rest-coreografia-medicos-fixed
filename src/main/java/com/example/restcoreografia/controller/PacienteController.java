package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Paciente;
import com.example.restcoreografia.producer.PacienteProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/V1/api/pacientes")
public class PacienteController {

    private Map<Long, Paciente> pacientes = new HashMap<>();
    private Long idCounter = 4L; // ya vamos a cargar 3 mocks

    @Autowired
    private PacienteProducer producer;

    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        paciente.setId(idCounter++);
        pacientes.put(paciente.getId(), paciente);

        // publicar evento paciente-creado
        producer.enviarPacienteCreado(paciente);

        return paciente;
    }

    @GetMapping
    public Collection<Paciente> listar() {
        return pacientes.values();
    }

    @GetMapping("/{id}")
    public Paciente obtener(@PathVariable Long id) {
        return pacientes.get(id);
    }

    @PostConstruct
    public void initMockData() {
        pacientes.put(1L, new Paciente(1L, "Juan Pérez", "CC123456", "juan.perez@example.com"));
        pacientes.put(2L, new Paciente(2L, "María López", "CC789012", "maria.lopez@example.com"));
        pacientes.put(3L, new Paciente(3L, "Carlos Gómez", "CC345678", "carlos.gomez@example.com"));
    }
}
