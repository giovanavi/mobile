package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
//import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Toast;
//import android.widget.Toast;

//import com.example.consultasqx.MapsActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.UserProfileActivity;
import com.example.consultasqx.Util.ConfiguraBD;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity{
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = ConfiguraBD.FirebaseAutenticacao();

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));


    }


    public void deslogar(View v){
        try{
            auth.signOut();
            finish();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void abrirPerfil(View v) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void abrirMarcarConsulta(View v){
        Intent intent = new Intent(this, SearchMedicoActivity.class);
        startActivity(intent);
    }

    public void abrirHistorico(View v){
        Intent intent = new Intent(this, HistoricoConsultas.class);
        startActivity(intent);
    }
}