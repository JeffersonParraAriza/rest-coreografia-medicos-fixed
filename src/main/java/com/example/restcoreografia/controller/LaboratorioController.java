package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Examen;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/V1/api/laboratorio")
public class LaboratorioController {

    private Map<Long, List<Examen>> examenes = new HashMap<>();
    private Long idCounter = 4L; // tenemos 3 mocks precargados

    @PostMapping("/ordenes/{pacienteId}")
    public Examen registrarLaboratorio(@PathVariable Long pacienteId, @RequestBody Examen examen) {
        examen.setId(idCounter++);
        examen.setPacienteId(pacienteId);
        examenes.computeIfAbsent(pacienteId, k -> new ArrayList<>()).add(examen);
        return examen;
    }

    @GetMapping("/resultados/{pacienteId}")
    public List<Examen> listar(@PathVariable Long pacienteId) {
        return examenes.getOrDefault(pacienteId, Collections.emptyList());
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
