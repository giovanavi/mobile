package com.example.consultasqx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.consultasqx.view.FormSignin;
import com.example.consultasqx.view.FormSignupPaciente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Sign_in(View view){
        Intent intent = new Intent(this, FormSignin.class);
        startActivity(intent);
    }

    public void Sign_up(View view){
        Intent intent = new Intent(this, FormSignupPaciente.class);
        startActivity(intent);
    }

}