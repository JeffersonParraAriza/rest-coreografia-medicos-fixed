package com.example.restcoreografia.controller;

import com.example.restcoreografia.model.HistorialMedico;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/V1/pacientes")
public class LaboratorioController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Map<String, HistorialMedico> store = new HashMap<>();
    private static final String TOPIC = "coreografia-topic";

    public LaboratorioController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public HistorialMedico crearPaciente(@RequestBody HistorialMedico historial) {
        String id = UUID.randomUUID().toString();
        historial.setId(id);
        store.put(id, historial);
        kafkaTemplate.send(TOPIC, "Nuevo historial creado: " + id);
        return historial;
    }

    @GetMapping("/{id}")
    public HistorialMedico  consultarPaciente(@PathVariable String id) {
        System.out.println("TEST Jefferson");
        return store.get(id);
    }

    @GetMapping("/{id}")
    public HistorialMedico actualizarPaciente(@PathVariable String id) {
        System.out.println("TEST Jefferson");
        return store.get(id);
    }
}
