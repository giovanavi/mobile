package com.example.helloworldandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText txtView1;
    EditText txtView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView1 = findViewById(R.id.txtView01);
        txtView2 = findViewById(R.id.txtView02);

    }

    public void onclickeButton01( View view ){

        String textoTxtView1 = txtView1.getText().toString();
        txtView1.setText("");

        txtView2.setText(textoTxtView1);

    }

}