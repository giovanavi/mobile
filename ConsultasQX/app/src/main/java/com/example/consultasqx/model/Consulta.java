package com.example.consultasqx.model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Consulta {

    private static int CONTADOR = 0;

    private String uid;
    private String medico; // id_medico
    private String nomeMedico;
    private String paciente; //id_paciente
    private String data; //String
    private String horario; //String
    private String local;
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

//    public ArrayList<Consulta> getList(){
//        Medico medico = new Medico();
//        ArrayList<Medico> lista = medico.getList();
//        Paciente paciente = new Paciente();
//        ArrayList<Paciente> listaPaciente = paciente.getList();
//
//        String hora1 = "12:00";
//        String hora2 = "17:00";
//
//        String data1 = "16/11/2022";
//        String data2 = "21/11/2022";
//
//        ArrayList<Consulta> consultaArrayList = new ArrayList<>();
//        consultaArrayList.add(new Consulta(lista.get(1), listaPaciente.get(0), stringToDate(data1), stringToTime(hora1), listaPaciente.get(0).getConvenio(), lista.get(1).getEspecialidade()));
//        consultaArrayList.add(new Consulta(lista.get(3), listaPaciente.get(0), stringToDate(data2), stringToTime(hora2), listaPaciente.get(0).getConvenio(), lista.get(3).getEspecialidade()));
//        consultaArrayList.add(new Consulta(lista.get(2), listaPaciente.get(0), stringToDate(data1), stringToTime(hora2), listaPaciente.get(0).getConvenio(), lista.get(2).getEspecialidade()));
//        consultaArrayList.add(new Consulta(lista.get(0), listaPaciente.get(0), stringToDate(data2), stringToTime(hora1), listaPaciente.get(0).getConvenio(), lista.get(0).getEspecialidade()));
//
//        return consultaArrayList;
//    }

    public Time stringToTime(String horario){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            Date data = formatador.parse(horario);
            time = new Time(data.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public Date stringToDate(String horario){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Time date = null;
        try {
            Date data = formatter.parse(horario);
            date = new Time(data.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

//    public Consulta addConsulta(Medico medico, Paciente paciente, Time horario, Date data, String especialidade){
//        Consulta consulta = new Consulta(medico, paciente, data, horario, paciente.getConvenio(), especialidade);
//
//        consultaArrayList.add(consulta);
//
//        return consulta;
//    }

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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String uid){
        this.uid = uid;
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
