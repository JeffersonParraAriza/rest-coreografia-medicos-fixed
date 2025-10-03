package com.example.restcoreografia.model;

public class HistorialMedico {
    private String id;
    private String paciente;
    private String diagnostico;

    public HistorialMedico() {}

    public HistorialMedico(String id, String paciente, String diagnostico) {
        this.id = id;
        this.paciente = paciente;
        this.diagnostico = diagnostico;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
}
