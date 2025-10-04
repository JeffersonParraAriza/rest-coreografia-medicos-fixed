package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Paciente;
import com.example.restcoreografia.producer.PacienteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private Map<Long, Paciente> pacientes = new HashMap<>();
    private Long idCounter = 1L;

    @Autowired
    private PacienteProducer producer;

    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        paciente.setId(idCounter++);
        pacientes.put(paciente.getId(), paciente);
        producer.enviarPacienteCreado(paciente);
        return paciente;
    }

    @GetMapping
    public Collection<Paciente> listar() {
        return pacientes.values();
    }
}
