package com.example.consultasqx.model;

import java.sql.Time;

public class Consulta {

    private int id;
    private Medico medico;
    private Paciente paciente;
    private Time horario;
    private String local;
    private String convenio;

    public Consulta(Medico medico, Paciente paciente, Time horario, String local, String convenio) {
        this.medico = medico;
        this.paciente = paciente;
        this.horario = horario;
        this.local = local;
        this.convenio = convenio;
    }

    public Consulta(Medico medico, Paciente paciente, Time horario, String local) {
        this.medico = medico;
        this.paciente = paciente;
        this.horario = horario;
        this.local = local;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
}
