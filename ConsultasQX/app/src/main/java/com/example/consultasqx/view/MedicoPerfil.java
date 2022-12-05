package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuPopupWindow;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.MapsActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MedicoPerfil extends AppCompatActivity {

    //    private ImageView img_perfil;
    private TextView txtNome;
    private TextView txtCrm;
    private Button btn_horarios;

    private ListView lista_especialidades;
    private ListView lista_convenios;

    ArrayAdapter adapterEspecialidades;
    ArrayAdapter adapterConvenios;

    String id;
    Medico medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_perfil);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        id = (String) getIntent().getExtras().get("id");


<<<<<<< HEAD
        //estou pegando o objeto Medico baeado na id vindo da activity anterior
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
        dr.child("Medico").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isComplete()){
                    medico = task.getResult().getValue(Medico.class);
                    initAdapters();
                    initComponentes();
                    Log.i("FIREBASE123", medico.toString());
                    System.out.println("ID EXTRA-> "+id);
                    System.out.println("MEDICO -> "+medico.getId());
                    System.out.println("MEDICO -> "+medico.toString());
                    System.out.println("MEDICO -> "+medico.getEspecialidades());
                    System.out.println("MEDICO -> "+medico.getHorarios());
                }else{
                    Log.i("FIREBASE123", "erro em trazer as informações do médico");
                }
            }
        });

        //antes pegava da lista, agora vai buscar somente o id específico do banco
//        ArrayList<Medico> lista;// = null;
//        lista = medico.getList();
//        medico = lista.get(id); //xxxxx


    }

    public void verHorarios(View view){
        btn_horarios = findViewById(R.id.verHorarios);

        Intent intent = new Intent(view.getContext(), AgendarConsulta.class);

        intent.putExtra("id", id);

        startActivity(intent);


    }

    public void initAdapters(){
        adapterEspecialidades = new ArrayAdapter(this, android.R.layout.simple_list_item_1, medico.getEspecialidades());
        adapterConvenios = new ArrayAdapter(this, android.R.layout.simple_list_item_1, medico.getConvenios());
=======
        ArrayList<Medico> lista;// = null;
        lista = medico.getList();
        medico = lista.get(id); //xxxxx

        initComponentes();
    }

    public void verHorarios(View view){
        Intent intent = new Intent(view.getContext(), AgendarConsulta.class);

        intent.putExtra("id_medico", id);
        startActivity(intent);
>>>>>>> 2564a445563ba7b3aeaf55fab391421ebc70e1d9
    }

    public void initComponentes(){

        txtNome = findViewById(R.id.nome);
        txtCrm = findViewById(R.id.crm);

        lista_especialidades = findViewById(R.id.list_especialidades);
        lista_convenios = findViewById(R.id.list_convenios);

        txtNome.setText(medico.getNome());
        lista_especialidades.setAdapter(adapterEspecialidades);
        lista_convenios.setAdapter(adapterConvenios);
        txtCrm.setText(medico.getCrm());


        btn_horarios = findViewById(R.id.verHorarios);
    }

    public void abrirLocal(View view){
        Intent intent = new Intent(view.getContext(), MapsActivity.class);

        intent.putExtra("id", id);

        startActivity(intent);
    }

    public void voltarMedicoPerfil(View view){
        finish();
    }
}