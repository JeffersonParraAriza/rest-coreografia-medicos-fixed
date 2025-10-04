package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.Examen;
import com.example.restcoreografia.producer.LaboratorioProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/V1/api/laboratorio")
public class LaboratorioController {

    private Map<Long, List<Examen>> examenes = new HashMap<>();
    private Long idCounter = 1L;

    @Autowired
    private LaboratorioProducer producer;

    @PostMapping("/{pacienteId}")
    public Examen registrar(@PathVariable Long pacienteId, @RequestBody Examen examen) {
        examen.setId(idCounter++);
        examen.setPacienteId(pacienteId);
        examenes.computeIfAbsent(pacienteId, k -> new ArrayList<>()).add(examen);
        producer.enviarResultadoDisponible(examen);
        return examen;
    }

    @GetMapping("/{pacienteId}")
    public List<Examen> listar(@PathVariable Long pacienteId) {
        return examenes.getOrDefault(pacienteId, new ArrayList<>());
    }
}
