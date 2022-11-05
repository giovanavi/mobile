package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.adapter.MedicoAdapter;

import java.util.ArrayList;

public class MarcarConsulta extends AppCompatActivity {

    RecyclerView recyclerView;
    MedicoAdapter adapter;

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
        setContentView(R.layout.activity_marcar_consulta);

        recyclerView = findViewById(R.id.recyclerViewMedicos);

        ArrayList<Medico> lista = getList();

        adapter = new MedicoAdapter(lista);


        initAutoCompletes();
        initRecyclerView();

    }

    //fazer funcionalidade para tornar o campo CONVENIO enabled se a opção de pagamento particular for marcada

    public void pesquisar(){
        //filtrar a pesquisa dos médicos pela Especialidada, Convenio
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public ArrayList<Medico> getList(){
        ArrayList lista = new ArrayList<Medico>();
        Medico m1 = new Medico("José", "9876", "Pediatra");
        Medico m2 = new Medico("Maria", "2134", "Psicologa");
        Medico m3 = new Medico("Jão", "4324", "Medica");
        Medico m4 = new Medico("Carolina", "2356", "Ortopedista");
        Medico m5 = new Medico("Arthur", "2518", "Clinico Geral");
        Medico m6 = new Medico("Manuel", "9841", "Psiquiatra");
        Medico m7 = new Medico("Manuel", "9841", "Psiquiatra");
        Medico m8 = new Medico("Manuel", "9841", "Psiquiatra");
        Medico m9 = new Medico("Manuel", "9841", "Psiquiatra");
        Medico m10 = new Medico("Manuel", "9841", "Psiquiatra");

        lista.add(m1);
        lista.add(m2);
        lista.add(m3);
        lista.add(m4);
        lista.add(m5);
        lista.add(m6);
        lista.add(m7);
        lista.add(m8);
        lista.add(m9);
        lista.add(m10);

        return lista;
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