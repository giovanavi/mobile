package com.example.consultasqx.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.view.AgendarConsulta;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder> {

    ArrayList<Consulta> consultaList;

    public ConsultaAdapter(ArrayList<Consulta> consultaList){
        this.consultaList = consultaList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView especialidade;
        private TextView tipo_consulta;
        private TextView convenio;
        private TextView horario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            especialidade = itemView.findViewById(R.id.especialidade);
            tipo_consulta = itemView.findViewById(R.id.tipo_consulta);
            convenio = itemView.findViewById(R.id.convenio);
            horario = itemView.findViewById(R.id.horario);
        }
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_consulta_adapter, parent, false);

        return new ConsultaAdapter.ViewHolder(itemView);
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

    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.ViewHolder holder, int position) {
//        new Consulta(medico, paciente, data, horario, paciente.getConvenio(), especialidade;

        String name = consultaList.get(position).getMedico().getNome();
        String especialidade = consultaList.get(position).getEspecialidade();
        String tipo_consulta = "Convenio: ";//consultasList.get(position).getTipo_consulta();
        String convenio = consultaList.get(position).getPaciente().getConvenio();
        Time hora = consultaList.get(position).getHorario();
        int id = consultaList.get(position).getId();
//        int id_medico = consultaList.get(position).getMedico().getId();
//        int id_paciente = consultaList.get(position).getPaciente().getId();

        holder.nome.setText(name);
        holder.especialidade.setText(especialidade);
        holder.tipo_consulta.setText(tipo_consulta);
        holder.horario.setText(hora.toString());
        if(tipo_consulta.equals("Convenio: ")) {
            holder.convenio.setText(convenio);
        }else{
            holder.convenio.setText("");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, Consulta.class);

//                intent.putExtra("id", id); // id da consulta
//                intent.putExtra("id_medico", id_medico);
//                intent.putExtra("id_paciente", id_paciente);

//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return consultaList.size();
    }


}