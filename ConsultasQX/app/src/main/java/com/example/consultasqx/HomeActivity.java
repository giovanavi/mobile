package com.example.consultasqx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.consultasqx.Util.ConfiguraBD;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = ConfiguraBD.FirebaseAutenticacao();
    }

    public void deslogar(View v){
        try{
            auth.signOut();
            finish();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}