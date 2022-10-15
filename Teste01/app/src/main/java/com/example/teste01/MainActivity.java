package com.example.teste01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.teste01.model.Contato;
import com.example.teste01.transactions.Constants;
import com.example.teste01.view.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contato> listaContatos;
//    ArrayAdapter adapter;
//    ListView listViewContatos;
    int selected;

    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaContatos = new ArrayList<>();

//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaContatos);

//        listViewContatos = findViewById(R.id.listViewContatos);
//        listViewContatos.setAdapter(adapter);
//        listViewContatos.setSelector(android.R.color.holo_blue_light);
//
//        listViewContatos.setOnItemClickListener((adapterView, view, position, l) -> {
//            Toast.makeText(MainActivity.this, ""+listaContatos.get(position).toString(),
//                    Toast.LENGTH_SHORT).show();
//            selected = position;
//        });

        //Fazem a msm coisa
//        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(MainActivity.this, ""+listaContatos.get(position).toString(),
//                        Toast.LENGTH_SHORT).show();
//                selected = position;
//            }
//        });


        //RECYCLE VIEW
        customAdapter = new CustomAdapter( listaContatos );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerViewContatos);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration( new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu( menu );
    }
    
    
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(MainActivity.this, ""+item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                clicarApagar();
                break;
            case R.id.settings:
                break;
            case R.id.about:
                break;
        }
        return true;

    }

    private void clicarApagar(){
        if (selected >= 0) {
            listaContatos.remove(selected);
            //notifica o adapter que o dataSet que ele usa como fonte foi modificado
            //no caso foi deletado um item
//            adapter.notifyDataSetChanged();
            customAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Selecione um item", Toast.LENGTH_SHORT).show();
        }
    }

    public void clicarAdicionar(){
        //Intent (*intenção*) de instanciar uma tela
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult( intent, Constants.REQUEST_ADD );

        String x = ""+Constants.REQUEST_ADD;
        Toast.makeText(this, "ClicarAdicionar - "+x, Toast.LENGTH_SHORT).show();

        //Iniciar Activity declanara no intent
        //Activity iniciada não tem informações sobre o retorno
        //startActivity(intent);
        //Iniciar Activity esperando um resultado
        //O retorno vai ser capturado pelo método onAtivityResult
        //startActivityForResult(intent, REQUEST_NUMBER)
    }

    public void clicarEditar(){
        Intent intent = new Intent(this, ContactActivity.class);

        Contato contato = listaContatos.get(selected);
//        intent.putExtra("id", contato.getId());
        intent.putExtra("nome", contato.getNome());
        intent.putExtra("telefone", contato.getTelefone());
        intent.putExtra("endereco", contato.getEndereco());

//        intent.putExtra("nome", listaContatos.get(selected));

        startActivityForResult( intent, Constants.REQUEST_EDIT );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD) {
            String nome = (String) data.getExtras().get("nome");
            String telefone = (String) data.getExtras().get("telefone");
            String endereco = (String) data.getExtras().get("endereco");

            listaContatos.add(new Contato(nome, telefone, endereco));
//            adapter.notifyDataSetChanged();
            customAdapter.notifyDataSetChanged();

        }else if(requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD){
            String nome = (String) data.getExtras().get("nome");
            String telefone = (String) data.getExtras().get("telefone");
            String endereco = (String) data.getExtras().get("endereco");

            listaContatos.set(selected, new Contato(nome, telefone, endereco));
//            adapter.notifyDataSetChanged();
            customAdapter.notifyDataSetChanged();

        }else if(resultCode == Constants.RESULT_CANCEL){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }
    }
}