package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Examen;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private Map<Long, List<Examen>> examenes = new HashMap<>();
    private Long idCounter = 4L;

    @PostMapping("/ordenes/{pacienteId}")
    public Examen registrarLaboratorio(@PathVariable Long pacienteId, @RequestBody Examen examen) {
        examen.setId(idCounter++);
        examen.setPacienteId(pacienteId);
        examenes.computeIfAbsent(pacienteId, k -> new ArrayList<>()).add(examen);
        return examen;
    }

    @PostMapping("/resultados/{pacienteId}/{examenId}")
    public ResponseEntity<?> registrarResultadoExamen(
            @PathVariable Long pacienteId,
            @PathVariable Long examenId,
            @RequestBody Examen examen) {

        List<Examen> examenesPaciente = examenes.get(pacienteId);
        Map<String, String> response = new HashMap<>();
        if (examenesPaciente == null) {
            response.put("mensaje", "No se encontraron exámenes para el paciente: " + pacienteId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        Optional<Examen> examenExistente = examenesPaciente.stream()
                .filter(e -> e.getId().equals(examenId))
                .findFirst();

        if (examenExistente.isEmpty()) {
            response.put("mensaje", "No se encontró el examen con ID: " + examenId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        examenExistente.get().setResultado(examen.getResultado());
        return ResponseEntity.ok(examenExistente.get());
    }

    @PostConstruct
    public void initMockData() {
        List<Examen> examenesPaciente1 = new ArrayList<>();
        examenesPaciente1.add(new Examen(1L, 1L, "Hemograma", "Normal", "2024-09-15"));
        examenesPaciente1.add(new Examen(2L, 1L, "Glucosa", "Elevada", "2024-09-20"));

        List<Examen> examenesPaciente2 = new ArrayList<>();
        examenesPaciente2.add(new Examen(3L, 2L, "Colesterol", "Alto", "2024-10-01"));

        examenes.put(1L, examenesPaciente1);
        examenes.put(2L, examenesPaciente2);
    }
}
