package com.example.consultasqx.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.view.ConsultaGeral;
import com.google.gson.internal.bind.JsonTreeReader;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder> implements Filterable {

    ArrayList<Consulta> consultaList;
    ArrayList<Consulta> consultasListFilter;

    public ConsultaAdapter(ArrayList<Consulta> consultaList){
        this.consultaList = consultaList;
        this.consultasListFilter = consultaList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null || charSequence.length() == 0){
                    filterResults.values = consultasListFilter;
                    filterResults.count = consultasListFilter.size();
                }else{
                    String search = charSequence.toString().toLowerCase();
                    ArrayList<Consulta> consultas = new ArrayList<>();

                    for (Consulta consulta: consultasListFilter) {
                        if(consulta.getNomeMedico().toLowerCase().contains(search) || consulta.getEspecialidade().contains(search) ){
                            consultas.add(consulta);
                        }
                    }

                    filterResults.values = consultas;
                    filterResults.count = consultas.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                consultaList = (ArrayList<Consulta>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_consulta_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.ViewHolder holder, int position) {

        String name = consultaList.get(position).getNomeMedico();
        String especialidade = consultaList.get(position).getEspecialidade();
        String tipo_consulta = "Convenio: ";//consultasList.get(position).getTipo_consulta();
        String conv = consultaList.get(position).getConvenio();
        String hora = consultaList.get(position).getHorario();
        String id_consulta = consultaList.get(position).getUid();

        String id_medico = consultaList.get(position).getMedico();

        holder.nome.setText(name);
        holder.especialidade.setText(especialidade);
        holder.tipo_consulta.setText(tipo_consulta);
        holder.horario.setText(hora);
        holder.convenio.setText(conv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ConsultaGeral.class);

                Toast.makeText(context, id_consulta+"", Toast.LENGTH_SHORT).show();

                intent.putExtra("id_medico", id_medico);
                intent.putExtra("id_consulta", id_consulta);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return consultaList.size();
    }


}