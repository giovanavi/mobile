package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.consultasqx.dao.DAOMedico;
import com.example.consultasqx.view.adapter.MedicoAdapter;
import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchMedicoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MedicoAdapter adapter;
    SearchView searchView;
    ArrayList<Medico> listaMedicos = new ArrayList<>();


    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medico);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

//        Medico medico = new Medico();
//        listaMedicos = medico.getList();
//        adapter = new MedicoAdapter(listaMedicos);


        initRecyclerView();
        initSearchView();
        attLista();
    }

    public void initSearchView(){
        searchView = findViewById(R.id.search_view);
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
        recyclerView = findViewById(R.id.recyclerViewMedicos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void attLista(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Medico").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMedicos.clear();
                //pegando cada elemento do banco e colocando dentro do listalivros
                for (DataSnapshot data: snapshot.getChildren()) {
                    Medico medico = data.getValue(Medico.class);
                    listaMedicos.add(medico);
                }
                //colocando a lista dentro do adapterMedicos
                adapter = new MedicoAdapter(listaMedicos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void voltarHomePage(View view){
        finish();
    }

}















