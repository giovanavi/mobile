package com.example.trabalho1;

public class Pessoa {

    private static int contadorId = 0;

    private int id;
    private String nome;
    private String nacionalidade;
    private String genero;

    public Pessoa(String nome, String nacionalidade, String genero) {
        this.id = contadorId++;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return nome + " - " + nacionalidade + " - " + genero;
    }
}
