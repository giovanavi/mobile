package com.example.consultasqx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consultasqx.MapsActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.UserProfileActivity;
import com.example.consultasqx.Util.ConfiguraBD;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity{
    private FirebaseAuth auth;

    private static final String TAG = "HomeActivity";
    //private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = ConfiguraBD.FirebaseAutenticacao();

        /*if(servicoAtivo()){

        }*/
    }

    private boolean servicoAtivo() {
        Log.d(TAG, "servicoAtivo: Verificando versão do google services");
        int disponivel = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(disponivel == ConnectionResult.SUCCESS){
            Log.d(TAG, "servicoAtivo: Serviços Google Play funcionando");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(disponivel)){
            Log.d(TAG, "servicoAtivo: Houve um erro que requer atenção");
        }else{
            Toast.makeText(this, "Não é possível fazer requisitos de mapa", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void deslogar(View v){
        try{
            auth.signOut();
            finish();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void abrirMapa(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void abrirPerfil(View v) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}