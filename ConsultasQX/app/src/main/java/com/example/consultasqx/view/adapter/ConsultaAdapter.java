package com.example.consultasqx.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Consulta;
import com.example.consultasqx.view.MedicoPerfil;

import java.util.ArrayList;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            especialidade = itemView.findViewById(R.id.especialidade);
            tipo_consulta = itemView.findViewById(R.id.tipo_consulta);
            convenio = itemView.findViewById(R.id.convenio);

        }
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_consulta_adapter, parent, false);

        return new ConsultaAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.ViewHolder holder, int position) {
        String name = consultaList.get(position).getMedico().getNome();
        String especialidade = consultaList.get(position).getMedico().getEspecialidade();
        String tipo_consulta = "Particular";
        String convenio = "HapVida";
        int id = consultaList.get(position).getId();

        holder.nome.setText(name);
        holder.especialidade.setText(especialidade);
        holder.tipo_consulta.setText(tipo_consulta);
        if(tipo_consulta.equals("Convenio")) {
            holder.convenio.setText(convenio);
        }else{
            holder.convenio.setText("");
        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, MedicoPerfil.class);
//
//                intent.putExtra("id", id);
//                intent.putExtra("nome", name);
//                intent.putExtra("especialidade", especialidade);
//                intent.putExtra("tipo_consulta", tipo_consulta);
//                intent.putExtra("convenio", convenio);
//
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return consultaList.size();
    }


}