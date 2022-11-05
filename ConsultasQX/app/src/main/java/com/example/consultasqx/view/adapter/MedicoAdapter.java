package com.example.consultasqx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultasqx.model.Medico;

import java.util.ArrayList;

public class MedicoAdapter extends RecyclerView.Adapter<MedicoAdapter.ViewHolder> {

    ArrayList<Medico> dataSet;

    public MedicoAdapter(ArrayList<Medico> dados){
        dataSet = dados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //    private ImageView img_perfil;
        private TextView nome;
        private TextView especialidade;
        private TextView crm;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            especialidade = itemView.findViewById(R.id.especialidade);
            crm = itemView.findViewById(R.id.crm);
        }


    }

    //cria o layout de cada linha (card)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medico_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    //exibe as insformações na linha (card)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = dataSet.get(position).getNome();
        String especialidade = dataSet.get(position).getEspecialidade();
        String crm = dataSet.get(position).getCrm();

        holder.nome.setText(name);
        holder.especialidade.setText(especialidade);
        holder.crm.setText(crm);

        holder.itemView.setOnClickListener(view ->
                Toast.makeText(view.getContext(), "clicando "+holder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show());

    }

    public int getItemCount() {
        return dataSet.size();
    }


}
