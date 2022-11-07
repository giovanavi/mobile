package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.adapter.MedicoAdapter;

import java.util.ArrayList;

public class BuscarPorFiltros extends AppCompatActivity {

    RecyclerView recyclerView;
    MedicoAdapter adapter;
    ArrayList<Medico> listaMedicos = new ArrayList<>();

    ArrayAdapter adapterEspecialidades;
    ArrayAdapter adapterConvenio;
    ArrayAdapter adapterPagamento;

    AutoCompleteTextView autoEspecialidade;
    String [] especialidades;
    AutoCompleteTextView autoConvenio;
    String [] convenios;
    AutoCompleteTextView autoPagamento;
    String [] pagamentos;

    Button btnPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_por_filtros);

        recyclerView = findViewById(R.id.recyclerViewMedicos);

        Medico medico = new Medico();
        listaMedicos = medico.getList();

        adapter = new MedicoAdapter(listaMedicos);

        initAutoCompletes();
        initRecyclerView();

    }

    //fazer funcionalidade para tornar o campo CONVENIO enabled se a opção de pagamento particular for marcada

    public void pesquisar(){
        btnPesquisa = findViewById(R.id.btnPesquisar);

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void initAutoCompletes(){
        //ArrayAdapter Especialidades / Para encher o autoComplete
        especialidades = getResources().getStringArray(R.array.especialidades_array);
        adapterEspecialidades = new ArrayAdapter(this, android.R.layout.simple_list_item_1, especialidades);
        autoEspecialidade = findViewById(R.id.autoCompleteEspecialidade);
        autoEspecialidade.setAdapter(adapterEspecialidades);
        autoEspecialidade.setText("");

        //ArrayAdapter Especialidades / Para encher o autoComplete
        convenios = getResources().getStringArray(R.array.convenio_array);
        adapterConvenio = new ArrayAdapter(this, android.R.layout.simple_list_item_1, convenios);
        autoConvenio = findViewById(R.id.autoCompleteConvenio);
        autoConvenio.setAdapter(adapterConvenio);
        autoEspecialidade.setText("");

        //ArrayAdapter Especialidades / Para encher o autoComplete
        pagamentos = getResources().getStringArray(R.array.pagamento_array);
        adapterPagamento = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pagamentos);
        autoPagamento = findViewById(R.id.autoCompletePagamento);
        autoPagamento.setAdapter(adapterPagamento);
        autoEspecialidade.setText("");
    }

}