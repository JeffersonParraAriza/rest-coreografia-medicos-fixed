package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Paciente;
import com.example.restcoreografia.producer.PacienteProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private Map<Long, Paciente> pacientes = new HashMap<>();
    private Long idCounter = 4L;

    @Autowired
    private PacienteProducer producer;

    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        paciente.setId(idCounter++);
        pacientes.put(paciente.getId(), paciente);
        return paciente;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPaciente(@PathVariable Long id) {
        Paciente paciente = pacientes.get(id);
        if (paciente == null) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "No se encontró ningún paciente con el ID: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (!pacientes.containsKey(id)) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "No se puede actualizar. No existe un paciente con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        paciente.setId(id);
        pacientes.put(id, paciente);
        return ResponseEntity.ok(paciente);
    }

    @PostConstruct
    public void initMockData() {
        pacientes.put(1L, new Paciente(1L, "Juan Pérez", "CC123456", "juan.perez@example.com"));
        pacientes.put(2L, new Paciente(2L, "María López", "CC789012", "maria.lopez@example.com"));
        pacientes.put(3L, new Paciente(3L, "Carlos Gómez", "CC345678", "carlos.gomez@example.com"));
    }
}
