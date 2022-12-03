package com.example.consultasqx.model;

import java.util.ArrayList;

public class Paciente extends Usuario{

    private static int CONTADOR = 0;

    private String uid;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String convenio;

    ArrayList<Paciente> pacienteArrayList = new ArrayList<>();

    public Paciente(){}

    public Paciente(String id, String nome, String cpf, String email, String senha, String telefone, String convenio) {
        this.uid = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.convenio = convenio;
    }

//    public ArrayList<Paciente> getList(){
//        pacienteArrayList = new ArrayList<>();
//
//        pacienteArrayList.add(new Paciente("Matias", "00000000000", "matias@email.com", "senha01", "09876543213", "HapVida"));
//
//        return pacienteArrayList;
//    }

//    public Paciente findPaciente(int id){
//        Paciente paciente = new Paciente();
//        pacienteArrayList = paciente.getList();
//        paciente = pacienteArrayList.get(id);
//        return paciente;
//    }

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + uid +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", convenio='" + convenio + '\'' +
                '}';
    }
}
