package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.consultasqx.R;

public class MarcarConsulta extends AppCompatActivity {

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

    ListView listViewMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_consulta);

        preencherAutoCompletes();

    }

    public void pesquisar(){

    }

    private void preencherAutoCompletes(){
        //ArrayAdapter Especialidades / Para encher o autoComplete
        especialidades = getResources().getStringArray(R.array.especialidades_array);
        adapterEspecialidades = new ArrayAdapter(this, android.R.layout.simple_list_item_1, especialidades);
        autoEspecialidade = findViewById(R.id.autoCompleteEspecialidade);
        autoEspecialidade.setAdapter(adapterEspecialidades);

        //ArrayAdapter Especialidades / Para encher o autoComplete
        convenios = getResources().getStringArray(R.array.convenio_array);
        adapterConvenio = new ArrayAdapter(this, android.R.layout.simple_list_item_1, convenios);
        autoConvenio = findViewById(R.id.autoCompleteConvenio);
        autoConvenio.setAdapter(adapterConvenio);

        //ArrayAdapter Especialidades / Para encher o autoComplete
        pagamentos = getResources().getStringArray(R.array.pagamento_array);
        adapterPagamento = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pagamentos);
        autoPagamento = findViewById(R.id.autoCompletePagamento);
        autoPagamento.setAdapter(adapterPagamento);
    }

}