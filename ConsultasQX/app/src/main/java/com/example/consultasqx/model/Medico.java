package com.example.consultasqx.model;

import java.util.ArrayList;
import java.util.List;

public class Medico{

    private String id;
    private String crm;
    private String nome;
    private String cpf;
    private String telefone;
    private List<String> especialidades;
    private List<String> convenios;
    private List<String> datas;
    private List<String> horarios;


    ArrayList<Medico> listaMedicos = new ArrayList<>();

    public Medico(){}

    public Medico(String id, String nome, String crm, String cpf, String telefone, List<String> especialidade, List<String> convenio) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.convenios= convenio;
        this.especialidades = especialidade;
    }

//    public ArrayList<Medico> getList(){
//
//        listaMedicos = new ArrayList<>();
//
//        listaMedicos.add(new Medico(0,"José", "9876","11111111111", "11111111111", "Dentista", "HapVida"));
//        listaMedicos.add(new Medico(1,"Maria", "2134", "22222222222", "22222222222", "Dentista", "ISSEC"));
//        listaMedicos.add(new Medico(2, "João", "4324", "33333333333", "33333333333","Radiologista", "UNIMED"));
//        listaMedicos.add(new Medico(3,"Carolina", "2356", "44444444444", "44444444444","Cardiologista", "UNIMED"));
//        listaMedicos.add(new Medico(4,"Arthur", "2518", "5555555555", "5555555555","Odontologista", "ISSEC"));
//        listaMedicos.add(new Medico(5,"Manuel", "9841", "66666666666", "66666666666","Oftalmologista", "HapVida"));
//        listaMedicos.add(new Medico(6,"Adriana", "3231", "77777777777", "77777777777","Nutricionista", "Bradesco Seguros"));
//        listaMedicos.add(new Medico(7,"Karine", "8121", "88888888888", "88888888888","Cirurgiã Dentista", "UNIMED"));
//        listaMedicos.add(new Medico(8,"Matheus", "4871", "99999999999", "99999999999","Psicólogo", "BB Seguros"));
//        listaMedicos.add(new Medico(9,"Anderson", "2345", "00000000000", "00000000000","Psicólogo`","HapVida"));
//
//        return listaMedicos;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public List<String> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<String> convenios) {
        this.convenios = convenios;
    }

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", convenio='" + convenios + '\'' +
                ", especialidade='" + especialidades + '\'' +
                '}';
    }
}
