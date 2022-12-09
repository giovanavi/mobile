package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.consultasqx.CadastroActivity;
import com.example.consultasqx.R;
import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.dao.DAOConsulta;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.model.Paciente;
import com.example.consultasqx.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgendarConsulta extends AppCompatActivity {

    CalendarView calendarView;
    Button btn_marcar_consulta;

    Spinner spinnerEspecialidades;
    Spinner spinnerHorarios;
    Spinner spinnerConvenios;
    Spinner spinnerDatas;

    ArrayAdapter<String> adapterEspecialidades;
    ArrayAdapter<String> adapterHorarios;
    ArrayAdapter<String> adapterConvenios;
    ArrayAdapter<String> adapterDatas;

    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
    FirebaseFirestore db;


    DAOConsulta daoConsulta;
    Medico medico;

    private FirebaseAuth auth;
    Usuario usuario;

    String date;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_consulta);

        db = FirebaseFirestore.getInstance();

        auth = ConfiguraBD.FirebaseAutenticacao();

        daoConsulta = new DAOConsulta();

        id = (String) getIntent().getExtras().get("id_medico");
        dr.child("Medico").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isComplete()){
                    medico = task.getResult().getValue(Medico.class);
                    initAdapters();
                    initSpinners();
                }else{
                    Log.i("FIREBASE", "erro em trazer as informações do médico");
                }
            }
        });

//        Query findMedico = FirebaseDatabase.getInstance().getReference("Medico")
//                .orderByChild("id").equalTo(id);
//        findMedico.addListenerForSingleValueEvent(valueEventListener);

//        calendarView = findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
//                month++;
//                date = day+"/"+ month+"/"+year;
//                checkDate();
//            }
//        });

        btn_marcar_consulta = findViewById(R.id.btn_marcar_consulta);

    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()){
//                for (DataSnapshot data: snapshot.getChildren()) {
//                    medico  = data.getValue(Medico.class);
//                    initAdapters();
//                    initSpinners();
//                }
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };

    public void initAdapters(){
        adapterEspecialidades = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getEspecialidades());
        adapterHorarios = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getHorarios());
        adapterConvenios = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getConvenios());
        adapterDatas = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getDatas());
    }

    public void initSpinners(){
        spinnerEspecialidades = findViewById(R.id.spinner_especialidade);
        spinnerHorarios = findViewById(R.id.spinner_horario);
        spinnerConvenios = findViewById(R.id.spinner_convenio);
        spinnerDatas = findViewById(R.id.spinner_data);

        //inicializando spinner de especialidades
        spinnerEspecialidades.setAdapter(adapterEspecialidades);

        //inicializando spinner de horarios
        spinnerHorarios.setAdapter(adapterHorarios);

        //inicializando spinner de convenios
        spinnerConvenios.setAdapter(adapterConvenios);

        //inicializando spinner de datas
        spinnerDatas.setAdapter(adapterDatas);

    }

    public void voltar(View view){
        finish();
    }

    public boolean checkSpinnerEspecialidades() {
        String check;
        check = (String) spinnerEspecialidades.getSelectedItem();

        if( check !=null ){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkSpinnerHorarios(){
        String check;
        check = (String) spinnerHorarios.getSelectedItem();

        if( check !=null ){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkSpinnerData(){
        String check;
        check = (String) spinnerDatas.getSelectedItem();

        if( check != null ){
            return true;
        }else{
            return false;
        }
    }

    public void addConsulta( View view ){
        if(checkSpinnerData()){
            if (checkSpinnerEspecialidades()){
                if (checkSpinnerHorarios()){

                    Consulta consulta = new Consulta();
                    String uid = consulta.setUid(UUID.randomUUID().toString());

                    consulta.setPaciente(auth.getUid());
                    consulta.setMedico(medico.getId());
                    consulta.setNomeMedico(medico.getNome());
                    consulta.setConvenio((String) spinnerConvenios.getSelectedItem());
                    consulta.setEspecialidade((String) spinnerEspecialidades.getSelectedItem());
                    consulta.setHorario((String) spinnerHorarios.getSelectedItem());
                    consulta.setData(((String) spinnerDatas.getSelectedItem()));
                    consulta.setCrm(medico.getCrm());

                    Map<String, Object> doc = new HashMap<>();
                    doc.put("id", uid);
                    doc.put("id_medico", consulta.getMedico());
                    doc.put("id_paciente", consulta.getPaciente());
                    doc.put("convenio", consulta.getConvenio());
                    doc.put("especialidade", consulta.getEspecialidade());
                    doc.put("horarios", consulta.getHorario());
                    doc.put("nome_medico", consulta.getNomeMedico());
                    doc.put("crm", consulta.getCrm());
                    doc.put("data", consulta.getData());


                    db.collection("Consulta").document(consulta.getUid()).set(doc)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AgendarConsulta.this, "Dados armazenados...", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AgendarConsulta.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

//
//                    daoConsulta.add(consulta).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(AgendarConsulta.this, "Consulta marcada", Toast.LENGTH_SHORT).show();
//
//                            finish();
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(AgendarConsulta.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(AgendarConsulta.this, "Falha em marcar a consulta", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                }else{
                    Toast.makeText(this, "Selecione um horário", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Selecione uma especialidade", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Selecione uma data", Toast.LENGTH_SHORT).show();
        }

    }

}
