package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.consultasqx.R;

public class FormSignupPaciente extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextSobrenome;
    EditText editTextEmail;
    EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_signup_paciente);

        editTextNome = findViewById(R.id.editTextNomePaciente);
        editTextSobrenome = findViewById(R.id.editTextSobrenomePaciente);
        editTextEmail = findViewById(R.id.editTextEmailPaciente);
        editTextSenha = findViewById(R.id.editTextSenhaPaciente);

    }


    public void criarPaciente(View view){
//        if email já existir, avisar
//        if email não existir fazer a conta e retornar para tela de login
    }

}