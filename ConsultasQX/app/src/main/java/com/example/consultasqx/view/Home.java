package com.example.consultasqx.view;

import androidx.annotation.NonNull;
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
import com.example.consultasqx.LoginActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.UserProfileActivity;
import com.example.consultasqx.Util.ConfiguraBD;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
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

        /*Bundle extras = getIntent().getExtras();

        if(extras != null){
            if(getIntent().getStringExtra("cadastrar").equals("cadastrou")){
                auth.signInWithEmailAndPassword(
                        getIntent().getStringExtra("email"),getIntent().getStringExtra("senha")
                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Home.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);

                            //abrirHome();
                        }else{
                            String excecao;

                            try{
                                throw Objects.requireNonNull(task.getException());
                            }catch(FirebaseAuthInvalidUserException e){
                                excecao = "Usuário não cadastrado";
                            }catch(FirebaseAuthInvalidCredentialsException e){
                                excecao = "Email ou senha incorreto";
                            }catch(Exception e){
                                excecao = "Erro ao logar o usuário " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(Home.this, excecao, Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
            }
        }*/
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
        //intent.putExtra("email", getIntent().getStringExtra("email"));
        //intent.putExtra("senha", getIntent().getStringExtra("senha"));
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