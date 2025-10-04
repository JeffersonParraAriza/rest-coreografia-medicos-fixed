package com.example.restcoreografia.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PacienteConsumer {

    @KafkaListener(topics = "paciente-creado", groupId = "historial")
    public void inicializarHistorial(String mensaje) {
        System.out.println("Inicializando historial para paciente: " + mensaje);
    }
}
