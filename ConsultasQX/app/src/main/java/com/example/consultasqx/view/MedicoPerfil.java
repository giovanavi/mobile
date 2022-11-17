package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.consultasqx.MapsActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;

import java.util.ArrayList;

import java.util.Objects;

public class MedicoPerfil extends AppCompatActivity {

    //    private ImageView img_perfil;
    private TextView txtNome;
    private TextView txtEspecialidade;
    private TextView txtCrm;
    private Button btn_horarios;

    private TextView txtEspecialidades;
    private TextView txtConvenios;

    int id;
    Medico medico = new Medico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_perfil);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        initComponentes();

        id = (int) getIntent().getExtras().get("id");

        ArrayList<Medico> lista;// = null;
        lista = medico.getList();
        medico = lista.get(id); //xxxxx

        initComponentes();
    }

    public void verHorarios(View view){

        btn_horarios = findViewById(R.id.verHorarios);

        btn_horarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AgendarConsulta.class);

                intent.putExtra("id_medico", id);
                startActivity(intent);

            }
        });

    }

    public void initComponentes(){

        txtNome = findViewById(R.id.nome);
        txtEspecialidade = findViewById(R.id.especialidade);
        txtCrm = findViewById(R.id.crm);

        txtEspecialidades = findViewById(R.id.txtEspecialidade);
        txtConvenios = findViewById(R.id.txtConvenio);


        txtNome.setText(medico.getNome());
        txtEspecialidade.setText(medico.getEspecialidade());
        txtCrm.setText(medico.getCrm());
        txtEspecialidades.setText(medico.getEspecialidade());
        txtConvenios.setText(medico.getConvenio());

//        if (getIntent() != null){
//            String nome = (String) getIntent().getExtras().get("nome");
//            String especialidade = (String) getIntent().getExtras().get("especialidade");
//            String crm = (String) getIntent().getExtras().get("crm");
//
//            txtNome.setText(nome);
//            txtEspecialidade.setText(especialidade);
//            txtCrm.setText(crm);
//        }
    }

    public void abrirLocal(View view){
        Intent intent = new Intent(view.getContext(), MapsActivity.class);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    public void voltarMedicoPerfil(View view){
        finish();
    }
}