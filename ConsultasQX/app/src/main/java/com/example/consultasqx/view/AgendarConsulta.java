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
import com.example.consultasqx.R;
import com.example.consultasqx.dao.DAOConsulta;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.model.Paciente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AgendarConsulta extends AppCompatActivity {

    CalendarView calendarView;
    Button btn_marcar_consulta;

    Spinner spinnerEspecialidades;
    Spinner spinnerHorarios;
    Spinner spinnerConvenios;
    ArrayAdapter<String> adapterEspecialidades;
    ArrayAdapter<String> adapterHorarios;
    ArrayAdapter<String> adapterConvenios;

    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();

    DAOConsulta daoConsulta;
    Medico medico;
    Paciente paciente;


    String date;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_consulta);

        paciente = new Paciente("0", "João", "098.765.432-11", "joao@email.com",
                "12345678", "88936172222", "HapVida");

        daoConsulta = new DAOConsulta();

        id = (String) getIntent().getExtras().get("id");

        dr.child("Medico").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isComplete()){
                    medico = task.getResult().getValue(Medico.class);
                    initAdapters();
                    initSpinners();
//                    Log.i("FIREBASE123", medico.toString());
//                    System.out.println("MEDICO -> "+medico.toString());
//                    System.out.println("MEDICO -> "+medico.getEspecialidades());
//                    System.out.println("MEDICO -> "+medico.getHorarios());
                }else{
                    Log.i("FIREBASE123", "erro em trazer as informações do médico");
                }
            }
        });


        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                date = day+"/"+ month+"/"+year;
                checkDate();
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    data = formatter.parse(date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }
        });

        btn_marcar_consulta = findViewById(R.id.btn_marcar_consulta);
//        btn_marcar_consulta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                marcarConsulta(view);
//            }
//        });

    }

    public void initAdapters(){
        adapterEspecialidades = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getEspecialidades());
        adapterHorarios = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getHorarios());
        adapterConvenios = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medico.getConvenios());

    }

    public void initSpinners(){
        spinnerEspecialidades = findViewById(R.id.spinner_especialidade);
        spinnerHorarios = findViewById(R.id.spinner_horario);
        spinnerConvenios = findViewById(R.id.spinner_convenio);

        //inicializando spinner de especialidades
        adapterEspecialidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidades.setAdapter(adapterEspecialidades);

        //inicializando spinner de horarios
        adapterHorarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarios.setAdapter(adapterHorarios);

        spinnerConvenios.setAdapter(adapterConvenios);

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

    public boolean checkDate(){
        if(date == null)
            return false;

//        for (String data: medico.getDatas()) {
//            if (data == date){
//                Toast.makeText(this, date+" - "+ data, Toast.LENGTH_SHORT).show();
//                return true;
//            }else{
//                Toast.makeText(this, "invalido "+ date +" + "+ data, Toast.LENGTH_SHORT).show();
//            }
//        }
        return true;
    }

    public void addConsulta( View view ){
        if(checkDate()){
            if (checkSpinnerEspecialidades()){
                if (checkSpinnerHorarios()){
                    Consulta consulta = new Consulta();
                    consulta.setUid(UUID.randomUUID().toString());
                    consulta.setPaciente(paciente.getId());
                    consulta.setMedico(medico.getId());
                    consulta.setNomeMedico(medico.getNome());
                    consulta.setConvenio((String) spinnerConvenios.getSelectedItem());
                    consulta.setEspecialidade((String) spinnerEspecialidades.getSelectedItem());
                    consulta.setHorario((String) spinnerHorarios.getSelectedItem());
                    consulta.setData((date));
                    consulta.setCrm(medico.getCrm());
                    consulta.setLocal("local x");

                    daoConsulta.add(consulta).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AgendarConsulta.this, "Consulta marcada", Toast.LENGTH_SHORT).show();

                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AgendarConsulta.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(AgendarConsulta.this, "Falha em marcar a consulta", Toast.LENGTH_SHORT).show();
                        }
                    });

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

//
//    public void marcarConsulta(View view){
//        if(checkDate()) {
//            if (checkSpinnerHorarios() && checkSpinnerEspecialidades()) {
//
//                Toast.makeText(this, "Consulta Marcada", Toast.LENGTH_SHORT).show();
////                finish();
//
//            }else if (!checkSpinnerHorarios() || !checkSpinnerEspecialidades()){
//                Toast.makeText(AgendarConsulta.this, "Selecione um horario e uma especialidade", Toast.LENGTH_SHORT).show();
//            }
//        }else if (!checkDate()){
//            Toast.makeText(AgendarConsulta.this, "Selecione uma data", Toast.LENGTH_SHORT).show();
//        }
//    }




//    public Time stringToTime(String horario){
//        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
//        Time time = null;
//        try {
//            Date data = formatador.parse(horario);
//            time = new Time(data.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return time;
//    }

//    public void marcarConsulta(View view){
//
//        Medico medico = new Medico();
//        ArrayList<Medico> listaM = medico.getList();
//        System.out.println(listaM.get(1).toString());
//
//        Paciente paciente = new Paciente();
//        ArrayList<Paciente> listaP = paciente.getList();
//        System.out.println(listaP.get(0).toString());
//
//
//        //pegando o radio button marcado em horarios
//        radioButton = radioGroupHorarios.findViewById(radioGroupHorarios.getCheckedRadioButtonId());
//        radioButton1 = radioGroupEspecialidades.findViewById(radioGroupEspecialidades.getCheckedRadioButtonId());
//
//        try {
//            if(checkDate()) {
//                if (checkRadioButtonHoarios() && checkRadioButtonEspecialidades()) {
//
//                    String horario = (String) radioButton.getText();
//                    Time hora = stringToTime(horario);
//                    String especialidade = (String) radioButton.getText();
//
//                    Consulta consulta = new Consulta(listaM.get(1), listaP.get(0), data, hora, especialidade, paciente.getConvenio());
//                    consulta.addConsulta(medico, paciente, hora, data, especialidade);
//
//                    Intent intent1= new Intent(view.getContext(),Home.class);
//
//                    Toast.makeText(AgendarConsulta.this, consulta.toString(), Toast.LENGTH_SHORT).show();
//                    System.out.println(consulta.toString());
//
//                    startActivity(intent1);
//
//                }else if (!checkRadioButtonHoarios() || !checkRadioButtonEspecialidades()){
//                    System.out.println("selecione um horario ou especialidade");
//                    Toast.makeText(AgendarConsulta.this, "Selecione um horario e uma especialidade", Toast.LENGTH_SHORT).show();
//                }
//            }else if (!checkDate()){
//                System.out.println("Selecione uma data");
//                Toast.makeText(AgendarConsulta.this, "Selecione uma data", Toast.LENGTH_SHORT).show();
//            }
//        }catch (NullPointerException e){
//            e.getMessage();
//        }
//
//    }

}
