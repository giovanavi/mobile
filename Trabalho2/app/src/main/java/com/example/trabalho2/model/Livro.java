package com.example.trabalho2.model;

public class Livro {

    private static int CONTADOR_ID = 0;

    private int id;
    private String titulo;
    private String autor;
    private String editora;

    public Livro(){}

    public Livro(String titulo, String autor, String editora) {
        this.id = CONTADOR_ID++;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + autor + " - " + editora;
    }
}
