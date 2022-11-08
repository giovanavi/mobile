package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.adapter.ConsultaAdapter;
import com.example.consultasqx.view.adapter.MedicoAdapter;

import java.util.ArrayList;

public class HistoricoConsultas extends AppCompatActivity {

    RecyclerView recyclerView;
    ConsultaAdapter adapter;
    SearchView searchView;
    ArrayList<Consulta> listaConsultas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_consultas);

        Consulta consulta = new Consulta();
        listaConsultas = consulta.getList();
        adapter = new ConsultaAdapter(listaConsultas);

        for (Consulta c: listaConsultas) {
            System.out.println("Medico " + c.getMedico().getNome());
            System.out.println(c.getPaciente().getNome());
            System.out.println("Convenio " + c.getConvenio());
        }

        initRecyclerView();

    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewConsultas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}