package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.consultasqx.MapsActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ConsultaGeral extends AppCompatActivity {

    TextView txtNome;
    TextView txtEspecialidade;
    TextView txtCrm;
    TextView txtData;

    Consulta consulta;

    String id;
    String id_medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_geral);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        id = (String) getIntent().getExtras().get("id_consulta");

        //estou pegando o objeto Consulta baeado na id vindo da activity anterior
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
        dr.child("Consulta").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isComplete()){
                    consulta = task.getResult().getValue(Consulta.class);
                    initComponentes();
                }else{
                    Log.i("FIREBASE", "erro em trazer as informações da consulta");
                }
            }
        });


    }


    public void initComponentes(){
        txtNome = findViewById(R.id.nomeMedico);
        txtEspecialidade = findViewById(R.id.especialidade);
        txtCrm= findViewById(R.id.crm);
        txtData = findViewById(R.id.data);

        txtNome.setText(consulta.getNomeMedico());
        txtEspecialidade.setText(consulta.getEspecialidade());
        txtCrm.setText(consulta.getCrm());
        String data = consulta.getData()+" "+consulta.getHorario();
        txtData.setText(data);
    }

    public void abrirLocal(View view){
        Intent intent = new Intent(view.getContext(), MapsActivity.class);

        intent.putExtra("id_medico", id_medico);

        startActivity(intent);
    }

    public void voltarMedicoPerfil(View view){
        finish();
    }


}