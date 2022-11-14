package com.example.consultasqx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.model.Medico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.consultasqx.R;

public class AgendarConsulta extends AppCompatActivity {

    CalendarView calendarView;
    Button btn_marcar_consulta;

    int id;
    int id_medico;
    int id_paciente;

    RadioGroup radioGroupHorarios;
    RadioGroup radioGroupConvenios;
    RadioGroup radioGroupEspecialidades;

    RadioButton radioButton;
    RadioButton radioButton1;
    RadioButton radioButton2;
    Date data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_consulta);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

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

                Toast.makeText(AgendarConsulta.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        radioGroupConvenios = findViewById(R.id.radioGroupConvenios);
        radioGroupHorarios.clearCheck();

        radioGroupEspecialidades = findViewById(R.id.radioGroupEspecialidades);
        radioGroupHorarios.clearCheck();




        id = (int) getIntent().getExtras().get("id");
        id_medico = (int) getIntent().getExtras().get("id_medico");
        id_paciente = (int) getIntent().getExtras().get("id_paciente");


//        initRadioGroup();
    }

    public boolean checkRadioButton(){
        radioButton = radioGroupHorarios.findViewById(radioGroupHorarios.getCheckedRadioButtonId());
        if(radioButton == null ){
            return false;
        }else{
            Toast.makeText(this, "Selecione um horário", Toast.LENGTH_SHORT).show();
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

//        List<String> convenios = medico.getConvenio();
//        RadioButton rb1 = findViewById(R.id.radioButtonConvenio1);
//        RadioButton rb2 = findViewById(R.id.radioButtonConvenio2);
//        RadioButton rb3 = findViewById(R.id.radioButtonConvenio3);
//
//        rb1.setText(convenios.get(0));
//        rb2.setText(convenios.get(1));
//        rb3.setText(convenios.get(2));

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
            time = new Time(String.valueOf(data.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public void marcarConsulta(View view){
        btn_marcar_consulta = findViewById(R.id.btn_marcar_consulta);

        btn_marcar_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Consulta consulta = new Consulta();
//                MedicoDAO medico = findMedico();
//                PacienteDAO paciente = findPaciente();

                //pegando o radio button marcado em horarios
                radioButton = radioGroupHorarios.findViewById(radioGroupHorarios.getCheckedRadioButtonId());
                String horario = (String) radioButton.getText();
                Time hora = stringToTime(horario);

                //pegando o radio button marcado em convenio
                radioButton1 = radioGroupConvenios.findViewById(radioGroupConvenios.getCheckedRadioButtonId());
                String convenio = (String) radioButton.getText();

                //pegando o radio button marcado em especialidade
                radioButton2 = radioGroupEspecialidades.findViewById(radioGroupEspecialidades.getCheckedRadioButtonId());
                String especialidade = (String) radioButton.getText();

                //pegar data com evento na main

                if (hora == null || data == null){
                    Toast.makeText(AgendarConsulta.this, "Selecione a data e o horário da consulta", Toast.LENGTH_SHORT).show();
                }
                if (convenio == null){
                    Toast.makeText(AgendarConsulta.this, "Selecione o convênio", Toast.LENGTH_SHORT).show();
                }
                if (especialidade == null){
                    Toast.makeText(AgendarConsulta.this, "Selecione a especialidade", Toast.LENGTH_SHORT).show();
                }

//                if ( consulta.marcarConsulta(medico, paciente, hora, data, convenio, especialidade) ){
//                    Intent intent = new Intent(view.getContext(), ConfirmConsulta);
//                    Toast.makeText(AgendarConsulta.this, "Sua consulta foi marcada com sucesso", Toast.LENGTH_SHORT).show();
//                else{

//                    Toast.makeText(AgendarConsulta.this, "Não foi possível marcar sua consulta", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

}