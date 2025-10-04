package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.HistorialMedico;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/V1/api/historial")
public class HistorialController {

    private Map<Long, List<HistorialMedico>> historiales = new HashMap<>();

    private Long idCounter = 7L;

    @PostMapping("/{pacienteId}")
    public HistorialMedico agregar(@PathVariable Long pacienteId, @RequestBody HistorialMedico h) {
        h.setId(idCounter++);
        h.setPacienteId(pacienteId);
        historiales.computeIfAbsent(pacienteId, k -> new ArrayList<>()).add(h);
        return h;
    }

    @GetMapping("/{pacienteId}")
    public List<HistorialMedico> listar(@PathVariable Long pacienteId) {
        return historiales.getOrDefault(pacienteId, Collections.emptyList());
    }

    @PostConstruct
    public void initMockData() {
        List<HistorialMedico> historial1 = new ArrayList<>();
        historial1.add(new HistorialMedico(1L, 1L, "Gripe común", "2024-09-01"));
        historial1.add(new HistorialMedico(2L, 1L, "Chequeo anual", "2024-10-15"));

        List<HistorialMedico> historial2 = new ArrayList<>();
        historial2.add(new HistorialMedico(3L, 2L, "Hipertensión", "2024-08-12"));
        historial2.add(new HistorialMedico(4L, 2L, "Examen de laboratorio: colesterol alto", "2024-09-20"));

        List<HistorialMedico> historial3 = new ArrayList<>();
        historial3.add(new HistorialMedico(5L, 3L, "Fractura de brazo", "2024-07-10"));
        historial3.add(new HistorialMedico(6L, 3L, "Rehabilitación física", "2024-08-05"));

        historiales.put(1L, historial1);
        historiales.put(2L, historial2);
        historiales.put(3L, historial3);
    }
}
