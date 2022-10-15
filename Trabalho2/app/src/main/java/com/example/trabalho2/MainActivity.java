package com.example.trabalho2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.trabalho2.model.Livro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_ADD = 11;
    private final int REQUEST_EDIT = 12;
    private final int RESULT_ADD = 21;
    private final int RESULT_CANCEL = 22;


    ArrayList<Livro> listaLivros;
    ArrayAdapter adapter;
    ListView listViewLivros;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaLivros = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaLivros);

        listViewLivros = findViewById(R.id.listViewLivros);
        listViewLivros.setAdapter(adapter);
        listViewLivros.setSelector(android.R.color.holo_red_light);

        listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+listaLivros.get(i).toString(),
                        Toast.LENGTH_SHORT).show();
                selected = i;
            }
        });

        listViewLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i;
                showPopupMenu(view);
                return true;
            }
        });

    }

    public boolean showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.delete:
                        clicarApagar();
                        break;
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        return true;
    }

    public void clickAdd( View view ){
        Intent intent = new Intent(this, BookActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    public void clickEdit( View view ){

        if( selected >= 0 ){
            Intent intent = new Intent(this, BookActivity.class);

            Livro livro = listaLivros.get(selected);

            intent.putExtra("id", livro.getId());
            intent.putExtra("titulo", livro.getTitulo());
            intent.putExtra("autor", livro.getAutor());
            intent.putExtra("editora", livro.getEditora());

            startActivityForResult( intent, REQUEST_EDIT );

        }else{
            Toast.makeText(this, "Selecione um item", Toast.LENGTH_SHORT).show();
        }

    }

    private void clicarApagar(){
        if (selected >= 0) {
            listaLivros.remove(selected);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Selecione um item", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ADD && resultCode == RESULT_ADD) {
            String titulo = (String) data.getExtras().get("titulo");
            String autor = (String) data.getExtras().get("autor");
            String editora = (String) data.getExtras().get("editora");

            listaLivros.add(new Livro(titulo, autor, editora));
            adapter.notifyDataSetChanged();

        }else if(requestCode == REQUEST_EDIT && resultCode == RESULT_ADD){
            String titulo = (String) data.getExtras().get("titulo");
            String autor = (String) data.getExtras().get("autor");
            String editora = (String) data.getExtras().get("editora");

            Livro livro = listaLivros.get(selected);
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setEditora(editora);

            adapter.notifyDataSetChanged();

        }else if(resultCode == RESULT_CANCEL){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }

    }
}