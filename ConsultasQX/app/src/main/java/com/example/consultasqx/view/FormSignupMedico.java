package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.consultasqx.R;

public class FormSignupMedico extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextSobrenome;
    EditText editTextEmail;
    EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_signup_medico);

        editTextNome = findViewById(R.id.editTextNomeMedico);
        editTextSobrenome = findViewById(R.id.editTextSobrenomeMedico);
        editTextEmail = findViewById(R.id.editTextEmailMedico);
        editTextSenha = findViewById(R.id.editTextSenhaMedico);

    }

    public void criarMedico(View view){
//        if email já existir, avisar
//        if email não existir fazer a conta e retornar para tela de login
    }

}