package com.example.restcoreografia.producer;

import com.example.restcoreografia.model.Examen;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LaboratorioProducer {
    private static final String TOPIC = "resultado-disponible";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public LaboratorioProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarResultadoDisponible(Examen examen) {
        kafkaTemplate.send(TOPIC, examen);
    }
}
