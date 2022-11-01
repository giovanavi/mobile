package com.example.consultasqx.model;

public class Login {

    private String email;
    private String senha;

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Login(){}

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

    public boolean autenticar(String email, String senha){

        if( this.email.equals(email) && this.senha.equals(senha) ){
            return true;
        }

        return false;
    }

}
