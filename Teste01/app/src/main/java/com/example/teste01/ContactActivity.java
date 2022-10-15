package com.example.teste01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teste01.transactions.Constants;

public class ContactActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTelefone;
    EditText edtEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Passando informação entre as telas (da 1a pra 2a)
        //String nome = (String) getIntent().getExtras().get("nome");
        //String sobrenome = (String) getIntent().getExtras().get("sobrenome");

        edtNome = findViewById(R.id.editNome);
        edtTelefone = findViewById(R.id.editTelefone);
        edtEndereco = findViewById(R.id.editEndereco);

        if(getIntent().getExtras() != null){
            String nome = (String) getIntent().getExtras().get("nome");
            String telefone = (String) getIntent().getExtras().get("telefone");
            String endereco = (String) getIntent().getExtras().get("endereco");

            edtNome.setText( nome );
            edtTelefone.setText( telefone );
            edtEndereco.setText( endereco );
        }

    }
    public void adicionar(View view){
        Intent intent = new Intent();

        String nome = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String telefone = edtTelefone.getText().toString();

        intent.putExtra("nome", nome);
        intent.putExtra("endereco", endereco);
        intent.putExtra("telefone", telefone);
        setResult(Constants.RESULT_ADD, intent);
        finish();

    }

    public void cancelar(View view){
        setResult(Constants.RESULT_CANCEL);
        finish();
    }
}