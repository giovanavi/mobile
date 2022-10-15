package com.example.trabalho2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {

    private final int RESULT_ADD = 21;
    private final int RESULT_CANCEL = 22;

    EditText editTitulo;
    EditText editAutor;
    EditText editEditora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        editTitulo = findViewById(R.id.editTitulo);
        editAutor = findViewById(R.id.editAutor);
        editEditora = findViewById(R.id.editEditora);

        if(getIntent().getExtras() != null){
            String titulo = (String) getIntent().getExtras().get("titulo");
            String autor = (String) getIntent().getExtras().get("autor");
            String editora = (String) getIntent().getExtras().get("editora");

            editTitulo.setText( titulo );
            editAutor.setText( autor );
            editEditora.setText(editora);

        }

    }

    public void adicionar( View view ){

        String titulo = editTitulo.getText().toString();
        String autor = editAutor.getText().toString();
        String editora = editEditora.getText().toString();

        if( !titulo.equals("") && !autor.equals("") && !editora.equals("") ){

            Intent intent = new Intent();

            intent.putExtra("titulo", titulo);
            intent.putExtra("autor", autor);
            intent.putExtra("editora", editora);

            setResult(RESULT_ADD, intent);
            finish();

        }else{
            Toast.makeText(this, "Preencha todos os  campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelar( View view ){
        setResult(RESULT_CANCEL);
        finish();
    }


}
