package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.consultasqx.view.adapter.MedicoAdapter;
import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;

import java.util.ArrayList;

import com.example.consultasqx.R;

public class SearchMedicoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MedicoAdapter adapter;
    SearchView searchView;
    ArrayList<Medico> listaMedicos = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medico);


        Medico medico = new Medico();
        listaMedicos = medico.getList();
        adapter = new MedicoAdapter(listaMedicos);

        initRecyclerView();
        initSearchView();
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
        recyclerView.setAdapter(adapter);
        
    }

    public void voltarHomePage(View view){
        finish();
    }

}















