package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.consultasqx.R;

public class AgendarConsulta extends AppCompatActivity {

    Button btn_marcar_consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_consulta);
    }

    public void marcarConsulta(View view){
        btn_marcar_consulta = findViewById(R.id.btn_marcar_consulta);

        btn_marcar_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}