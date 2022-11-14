package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.Home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;


import com.example.consultasqx.model.Paciente;
import com.example.consultasqx.view.adapter.ConsultaAdapter;

public class AgendarConsulta extends AppCompatActivity {

    CalendarView calendarView;
    Button btn_marcar_consulta;

    int id_medico;
    int id_paciente = 0;

    RadioGroup radioGroupHorarios;
    RadioGroup radioGroupEspecialidades;

    RadioButton radioButton;
    RadioButton radioButton1;
    Date data = null;

    ConsultaAdapter adapter;
    ArrayList<Consulta> listaConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_consulta);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        Consulta consulta = new Consulta();
        listaConsultas = consulta.getList();
        adapter = new ConsultaAdapter(listaConsultas);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = day + "/" + month + "/" + year;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = formatter.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AgendarConsulta.this, date, Toast.LENGTH_SHORT).show();
            }
        });

        radioGroupHorarios = findViewById(R.id.radioGroupHorarios);
        radioGroupHorarios.clearCheck();
        radioGroupHorarios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String marcado = (String) radioButton.getText();
                Toast.makeText(AgendarConsulta.this, marcado, Toast.LENGTH_SHORT).show();
            }
        });


        radioGroupEspecialidades = findViewById(R.id.radioGroupEspecialidades);
        radioGroupEspecialidades.clearCheck();
        radioGroupEspecialidades.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String marcado = (String) radioButton.getText();
                Toast.makeText(AgendarConsulta.this, marcado, Toast.LENGTH_SHORT).show();
            }
        });

//        id_medico = (int) getIntent().getExtras().get("id_medico");
//        id_paciente = (int) getIntent().getExtras().get("id_paciente");

        btn_marcar_consulta = findViewById(R.id.btn_marcar_consulta);
        btn_marcar_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marcarConsulta(view);
            }
        });

    }

    public boolean checkRadioButtonHoarios(){
        radioButton = radioGroupHorarios.findViewById(radioGroupHorarios.getCheckedRadioButtonId());
        if(radioButton == null ){
            return false;
        }
        return true;
    }

    public boolean checkRadioButtonEspecialidades(){
        radioButton1 = radioGroupEspecialidades.findViewById(radioGroupEspecialidades.getCheckedRadioButtonId());
        if(radioButton1 == null ){
            return false;
        }
        return true;
    }

    public boolean checkDate(){
        if (data == null){
            return false;
        }
        return true;
    }

    public void initRadioGroup(){
//        Medico medico = findMedico(int id);
//        List<Time> horarios = medico.getHorarios();
//
//        RadioButton rb1 = findViewById(R.id.radioButtonHorario1);
//        RadioButton rb2 = findViewById(R.id.radioButtonHorario2);
//        RadioButton rb3 = findViewById(R.id.radioButtonHorario3);
//
//        rb1.setText(horarios.get(0));
//        rb2.setText(horarios.get(1));
//        rb3.setText(horarios.get(2));

//        List<String> especialidades = medico.getEspecialidade();
//        RadioButton rb1 = findViewById(R.id.radioButtonEspecialidade1);
//        RadioButton rb2 = findViewById(R.id.radioButtonEspecialidade2);
//        RadioButton rb3 = findViewById(R.id.radioButtonEspecialidade3);
//
//        rb1.setText(convenios.get(0));
//        rb2.setText(convenios.get(1));
//        rb3.setText(convenios.get(2));

    }

    public Time stringToTime(String horario){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            Date data = formatador.parse(horario);
            time = new Time(data.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public void marcarConsulta(View view){
        Intent intent = new Intent(view.getContext(), Home.class);

        Toast.makeText(this, "Consulta Marcada", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

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
