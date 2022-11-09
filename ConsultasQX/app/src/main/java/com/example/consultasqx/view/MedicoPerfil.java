package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultasqx.R;

import com.example.consultasqx.R;

public class MedicoPerfil extends AppCompatActivity {

//    private ImageView img_perfil;
    private TextView txtNome;
    private TextView txtEspecialidade;
    private TextView txtCrm;
    private Button btn_horarios;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_perfil);

        initComponentes();

        id = (int) getIntent().getExtras().get("id");

    }

    public void verHorarios(View view){

        btn_horarios = findViewById(R.id.verHorarios);

        btn_horarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AgendarConsulta.class);

                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

    }

    public void initComponentes(){

        txtNome = findViewById(R.id.nome);
        txtEspecialidade = findViewById(R.id.especialidade);
        txtCrm = findViewById(R.id.crm);

        if (getIntent() != null){

            String nome = (String) getIntent().getExtras().get("nome");
            String especialidade = (String) getIntent().getExtras().get("especialidade");
            String crm = (String) getIntent().getExtras().get("crm");

            txtNome.setText(nome);
            txtEspecialidade.setText(especialidade);
            txtCrm.setText(crm);

        }
    }
}