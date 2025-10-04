package com.example.restcoreografia.producer;

import com.example.restcoreografia.model.Paciente;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PacienteProducer {
    private static final String TOPIC = "paciente-creado";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PacienteProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPacienteCreado(Paciente paciente) {
        kafkaTemplate.send(TOPIC, paciente);
    }
}
