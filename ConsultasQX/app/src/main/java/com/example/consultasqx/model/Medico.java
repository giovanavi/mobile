package com.example.consultasqx.model;

public class Medico extends Usuario{

    private int id;
    private int crm;
    private String nome;
    private String cpf;
    private String telefone;
    private String convenio;

    public Medico(int crm, String nome, String cpf, String telefone, String convenio) {
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.convenio = convenio;
    }

    public Medico(int crm, String nome, String cpf, String telefone) {
        this.crm = crm;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
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
}
