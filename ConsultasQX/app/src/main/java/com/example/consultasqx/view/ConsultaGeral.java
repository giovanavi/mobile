package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConsultaGeral extends AppCompatActivity {

    TextView txtNome;
    TextView txtEspecialidade;
    TextView txtCrm;
    TextView txtData;

    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
    Consulta consulta;


    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_geral);


        Query findConsulta = FirebaseDatabase.getInstance().getReference("Consulta")
                        .orderByChild("id").equalTo(id);
        findConsulta.addListenerForSingleValueEvent(valueEventListener);


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                for (DataSnapshot data: snapshot.getChildren()) {
                    consulta  = data.getValue(Consulta.class);
                    initComponents();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    public void initComponents(){
        txtNome = findViewById(R.id.nomeMedico);
        txtEspecialidade = findViewById(R.id.especialidade);
        txtCrm= findViewById(R.id.crm);
        txtData = findViewById(R.id.textViewData);

        txtNome.setText(consulta.getNomeMedico());
        txtEspecialidade.setText(consulta.getEspecialidade());
        txtCrm.setText(consulta.getCrm());

        String date = consulta.getData()+" "+consulta.getHorario();

        txtData.setText(date);
    }

    public void voltarMedicoPerfil(View view){
        finish();
    }


}