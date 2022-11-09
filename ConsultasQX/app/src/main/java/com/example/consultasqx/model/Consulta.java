package com.example.consultasqx.model;

import android.provider.ContactsContract;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Consulta {

    private static int CONTADOR=0;

    private int id;
    private Medico medico;
    private Paciente paciente;
    private Date data;
    private Time horario;
    private String local;
    private String tipo_consulta; //particular ou convenio
    private String convenio;

    public Consulta(){}

    public Consulta(Medico medico, Paciente paciente, Time horario, String local, String convenio) {
        this.id = CONTADOR++;
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

    public Consulta(Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public ArrayList<Consulta> getList(){

        Medico medico = new Medico();
        ArrayList<Medico> lista = medico.getList();

        ArrayList<Consulta> listaConsulta = new ArrayList<>();

        for (int i=0; i<10; i++){
            medico = lista.get(i);
            String nome = "Paciente " + i;
            listaConsulta.add(new Consulta(medico, new Paciente(nome)));
        }

        return listaConsulta;
    }

    public void marcarConsulta(Medico medico, Paciente paciente, Time horario, Date data){
        //código para criar a consulta
        Consulta consulta = new Consulta();
        //return true or false para poder informar ao Paciente
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

    public Date getData(){
        return this.data;
    }

    public void setData(Date date){
        this.data = date;
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

    public String getTipo_consulta() {
        return tipo_consulta;
    }

    public void setTipo_consulta(String tipo) {
        this.tipo_consulta = tipo;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public int getId(){
        return id;
    }

}
