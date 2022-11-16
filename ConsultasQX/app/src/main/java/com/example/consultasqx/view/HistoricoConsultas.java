package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.adapter.ConsultaAdapter;
import com.example.consultasqx.view.adapter.MedicoAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HistoricoConsultas extends AppCompatActivity {

    RecyclerView recyclerView;
    ConsultaAdapter adapter;
    SearchView searchView;
    ArrayList<Consulta> listaConsultas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_consultas);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        Consulta consulta = new Consulta();
        listaConsultas = consulta.getList();
        adapter = new ConsultaAdapter(listaConsultas);

        initRecyclerView();
        initSearchView();
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

    public void voltarHomePage(View v){
        finish();
    }
}
