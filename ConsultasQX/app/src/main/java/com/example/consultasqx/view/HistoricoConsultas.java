package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.model.Paciente;
import com.example.consultasqx.model.Usuario;
import com.example.consultasqx.view.adapter.ConsultaAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoricoConsultas extends AppCompatActivity {

    RecyclerView recyclerView;
    ConsultaAdapter adapter;
    SearchView searchView;
    ArrayList<Consulta> listaConsultas = new ArrayList<>();

    private FirebaseAuth auth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_consultas);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        auth = ConfiguraBD.FirebaseAutenticacao();

        initRecyclerView();
        initSearchView();
        attLista();
    }

    public void initSearchView(){
        searchView = findViewById(R.id.search_view_consulta);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewConsultas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void attLista(){

        databaseReference.child("Consulta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaConsultas.clear();
                Consulta c ;
                //pegando cada elemento do banco e colocando dentro do listalivros
                for (DataSnapshot data: snapshot.getChildren()) {
                    c = data.getValue(Consulta.class);
                    if(c.getPaciente().equals(auth.getUid())) {
                        listaConsultas.add(c);
                    }
                }
                //colocando a lista dentro do adapterMedicos
                adapter = new ConsultaAdapter(listaConsultas);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public void voltarHomePage(View v){
        finish();
    }
}
