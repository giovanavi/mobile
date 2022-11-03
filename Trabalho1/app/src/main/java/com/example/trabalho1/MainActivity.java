package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


//Toogle button (on/off por exemplo)  OK
//Edit text  ok
//Array   ok
//Autocomplete  ok
//Dropdown list (select options)  ok
//Radio button  ok
//OptionMenu  ok
//List view   ok
//ArrayAdapter no ListView ok
//Dropdow/popup Menu ok
//Clique longo no comportamento da tela ok
//Imagem de fundo
//Multiplas tabs
//Grid view
//Tocar um som


public class MainActivity extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*ArrayList<Pessoa> listaPessoas;
    ArrayAdapter adapterPessoas;
    ArrayAdapter adapterPaises;
    ArrayAdapter adapterGenero;

    String[] generos;
    String[] paises;

    int selected;

    ListView listViewPessoas;

    ToggleButton toggleButton;
    RadioGroup radioGroup;
    RadioButton radioButton;

    Button btn_add;
    Button btn_cancel;

    EditText edtNome;
    Spinner spinner;
    AutoCompleteTextView editNacionalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toggleButton
        toggleButton = findViewById(R.id.btn_toggle);
        toggleButton.setOnCheckedChangeListener(this);

        //radioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        //ArrayList e ArrayAdapter
        selected = -1;
        listaPessoas = new ArrayList<>();

        //ArrayAdapter Pessoas
        adapterPessoas = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaPessoas);
        //list View
        listViewPessoas = findViewById(R.id.listView);
        listViewPessoas.setAdapter(adapterPessoas);
        listViewPessoas.setSelector(android.R.color.holo_purple);
        //método indicação do nome do item
        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i;
            }
        });

        //show PopupMenu
        listViewPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i;
                showPopupMenu(view);
                return true;
            }
        });
        
        //ArrayAdapter PAISES
        paises = getResources().getStringArray(R.array.paises);
        adapterPaises = new ArrayAdapter(this, android.R.layout.simple_list_item_1, paises);
        //AutoCompleteTextView
        editNacionalidade = findViewById(R.id.autoCompleteNacionalidade);
        editNacionalidade.setAdapter(adapterPaises);

        //ArrayAdapter GENERO
        generos = getResources().getStringArray(R.array.genero);
        adapterGenero = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos);
        //Spinner (dropdown menu)
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapterGenero);

        //checar a marcação do radioButton
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            RadioButton rb = (RadioButton) group.findViewById(checkedId);
//            if(null!=rb && checkedId > -1){
//                Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });

        //chamada do método adicionar()
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });

        //chamada do método cacelar()
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

    }

    public boolean checkRadioButton(){
        RadioButton rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        if(rb == null ){
            return false;
        }
        if(rb.getText().equals("Não")){
            return false;
        }
        return true;
    }

    //Criação do menu na tab
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu( menu );
    }

    //Item do menu selecionado
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.add:
                adicionar();
                break;
            case R.id.delete:
                apagar();
                break;
        }
        return true;
    }

    //Show popup menu
    public boolean showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.delete:
                        apagar();
                        break;
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.popup_menu_main_activity);
        popupMenu.show();
        return true;
    }

    //metodo adicionar item ao ArrayList
    public void adicionar(){
        //adicionar um item na lista

        edtNome = findViewById(R.id.editName);
        editNacionalidade = findViewById(R.id.autoCompleteNacionalidade);
        spinner = findViewById(R.id.spinner);

        String nome = edtNome.getText().toString();
        String nacionalidade = editNacionalidade.getText().toString();
        String genero = generos[spinner.getSelectedItemPosition()];

        radioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        try {
            if (checkRadioButton()) {
                if(!nacionalidade.isEmpty()) {
                    listaPessoas.add(new Pessoa(nome, nacionalidade, genero));
                    adapterPessoas.notifyDataSetChanged();

                    edtNome.setText("");
                    editNacionalidade.setText("");
                    unCheak();
                }else{
                    Toast.makeText(this, "É preciso preencher todos os campos", Toast.LENGTH_SHORT).show();
                }
            }else if(!checkRadioButton()){
                Toast.makeText(this, "É preciso aceitar os termos", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e) {
            if(!checkRadioButton()){
                Toast.makeText(this, "É preciso aceitar os termos", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "É preciso preencher todos os campos", Toast.LENGTH_SHORT).show();
        }

    }

    //metodo apagar item ao ArrayList
    public void apagar(){
        //depois de selecionado um item apagar o mesmo

        if (selected >= 0) {
            listaPessoas.remove(selected);
            //notifica o adapter que o dataSet que ele usa como fonte foi modificado
            //no caso foi deletado um item
            adapterPessoas.notifyDataSetChanged();
        }else{
            Toast.makeText(MainActivity.this, "Selecione um item", Toast.LENGTH_SHORT).show();
        }

        unCheak();
    }


//    public void editar(){
//        //depois de selecionado um item editar o meesmo
//
//        if(selected >= 0){
//            edtNome = findViewById(R.id.editName);
//            edtNome.setText( listaPessoas.get(selected).getNome() );
//
//            String nome = edtNome.getText().toString();
//
//            listaPessoas.set(selected, new Pessoa(nome));
//            adapter.notifyDataSetChanged();
//
//            //marcar um opção do radio button
//        }else {
//            Toast.makeText(MainActivity.this, "Selecione um item", Toast.LENGTH_SHORT).show();
//        }
//        edtNome.setText("");
//        unCheak();
//    }

    //limpar os campos
    public void cancelar(){
        Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();

        edtNome.setText("");
        editNacionalidade.setText("");
        unCheak();

    }

    //desmarcar radioButton
    public void unCheak(){
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
    }

    //Toggle button
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(R.id.btn_toggle == compoundButton.getId())
            if(toggleButton.isChecked()){
                String result = "Botão toggle está On";
                Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            }else{
                String result = "Botão toggle está Off";
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }*/
}