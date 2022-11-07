package com.example.consultasqx.model;

import java.util.ArrayList;

public class Medico extends Usuario{

    private static int CONTADOR=0;

    private int id;
    private String crm;
    private String nome;
    private String cpf;
    private String telefone;
    private String convenio;
    private String especialidade;

    public Medico(){}

    public Medico(String nome, String crm, String especialidade){
        this.id = CONTADOR++;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, String nome, String cpf, String telefone, String convenio) {
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.convenio = convenio;
    }

    public Medico(String crm, String nome, String cpf, String telefone) {
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public ArrayList<Medico> getList(){
        ArrayList<Medico> listaMedicos = new ArrayList<>();
        listaMedicos.add(new Medico("José", "9876", "Pediatra"));
        listaMedicos.add(new Medico("Maria", "2134", "Psicologa"));
        listaMedicos.add(new Medico("Jão", "4324", "Medica"));
        listaMedicos.add(new Medico("Carolina", "2356", "Ortopedista"));
        listaMedicos.add(new Medico("Arthur", "2518", "Clinico Geral"));
        listaMedicos.add(new Medico("Manuel", "9841", "Psiquiatra"));
        listaMedicos.add(new Medico("Adriana", "3231", "Neurologista"));
        listaMedicos.add(new Medico("Karine", "8121", "Urologista"));
        listaMedicos.add(new Medico("Matheus", "4871", "Clinico Gera"));
        listaMedicos.add(new Medico("Anderson", "2345", "Dentista"));

        return listaMedicos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }
}
