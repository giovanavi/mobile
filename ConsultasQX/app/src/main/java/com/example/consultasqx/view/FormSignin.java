package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Login;

public class FormSignin extends AppCompatActivity {

    Login login = new Login("admin", "admin");

    EditText editTextEmail;
    EditText editTextSenha;
    Spinner perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_singin);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextPassword);
        perfil = findViewById(R.id.spinnerPerfil);
        //populando o spinner (MédicoPaciente)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pefil_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        perfil.setAdapter(adapter);


    }

    public void logar(View view, String email, String senha){
        email = editTextEmail.getText().toString();
        senha = editTextSenha.getText().toString();
//        String perfilSelected = (String) perfil.getSelectedItem();

//        if(perfilSelected.equals("Paciente")){
//            //Autenticar Paciente
//        }else{
//            //Autenticar Medico
//        }
        if(!login.autenticar(email, senha)){
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Email e senha corretos", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, Home.class);
        }
    }

    public void criaConta(View view){
        Intent intent = new Intent(this, FormSignupPaciente.class);

    }

}