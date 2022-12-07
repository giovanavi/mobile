package com.example.consultasqx.model;

import java.util.ArrayList;

public class Consulta {

    private static int CONTADOR = 0;

    private String uid;
    private String medico; // id_medico
    private String nomeMedico;
    private String paciente; //id_paciente
    private String data; //String
    private String horario; //String
    private String convenio;
    private String especialidade;
    private String crm;

    ArrayList<Consulta> consultaArrayList = new ArrayList<>();

    public Consulta(){}

    public Consulta(String medico, String nomeMedico, String paciente, String data, String horario, String convenio, String especialidade) {
        this.medico = medico;
        this.nomeMedico = nomeMedico;
        this.paciente = paciente;
        this.horario = horario;
        this.data = data;
        this.convenio = convenio;
        this.especialidade = especialidade;
        this.crm = crm;
//        this.uid = CONTADOR ++;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getNomeMedico(){
        return nomeMedico;
    }

    public void setNomeMedico(String nome){
        this.nomeMedico = nome;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String date){
        this.data = date;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getUid(){
        return uid;
    }

    public String setUid(String uid){
        this.uid = uid;
        return uid;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return medico + " - " + paciente + " - " + data + " - " + horario + " - "
                + convenio + " - " + especialidade;
    }
}
