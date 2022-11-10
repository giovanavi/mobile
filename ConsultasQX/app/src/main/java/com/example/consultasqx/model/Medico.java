package com.example.consultasqx.model;

import java.util.ArrayList;

public class Medico extends Usuario{

    private static int CONTADOR = 0;

    private int id;
    private String crm;
    private String nome;
    private String cpf;
    private String telefone;
    private String convenio;
    private String especialidade;

    ArrayList<Medico> listaMedicos = new ArrayList<>();

    public Medico(){}

    public Medico(String nome, String crm, String cpf, String telefone, String especialidade, String convenio) {
        this.id = CONTADOR++;
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.convenio = convenio;
        this.especialidade = especialidade;
    }

    public ArrayList<Medico> getList(){

        listaMedicos = new ArrayList<>();

        listaMedicos.add(new Medico("José", "9876","11111111111", "11111111111", "Pediatra", "HapVida"));
        listaMedicos.add(new Medico("Maria", "2134", "22222222222", "22222222222", "Psicologa", "ISSEC"));
        listaMedicos.add(new Medico("Jão", "4324", "33333333333", "33333333333","Clinico Geral", "UNIMED"));
        listaMedicos.add(new Medico("Carolina", "2356", "44444444444", "44444444444","Ortopedista", "UNIMED"));
        listaMedicos.add(new Medico("Arthur", "2518", "5555555555", "5555555555","Clinico Geral", "ISSEC"));
        listaMedicos.add(new Medico("Manuel", "9841", "66666666666", "66666666666","Psiquiatra", "HapVida"));
        listaMedicos.add(new Medico("Adriana", "3231", "77777777777", "77777777777","Neurologista", "Bradesco Seguros"));
        listaMedicos.add(new Medico("Karine", "8121", "88888888888", "88888888888","Urologista", "UNIMED"));
        listaMedicos.add(new Medico("Matheus", "4871", "99999999999", "99999999999","Clinico Gera", "BB Seguros"));
        listaMedicos.add(new Medico("Anderson", "2345", "00000000000", "00000000000","Dentista","HapVida"));

        return listaMedicos;
    }

    public Medico findMedico(int id){
        Medico medico = new Medico();

        listaMedicos = medico.getList();

        medico = listaMedicos.get(id);

        return medico;
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


    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", convenio='" + convenio + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
