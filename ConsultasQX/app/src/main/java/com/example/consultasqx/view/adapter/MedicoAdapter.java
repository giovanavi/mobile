package com.example.consultasqx.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultasqx.R;
import com.example.consultasqx.model.Medico;
import com.example.consultasqx.view.MedicoPerfil;

import java.util.ArrayList;

public class MedicoAdapter extends RecyclerView.Adapter<MedicoAdapter.ViewHolder> implements Filterable{

    ArrayList<Medico> medicosListFull;
    ArrayList<Medico> medicosListFilter;

    public MedicoAdapter(ArrayList<Medico> dados){
        this.medicosListFull = dados;
        this.medicosListFilter = dados;
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

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null || charSequence.length() == 0){
                    filterResults.values = medicosListFilter;
                    filterResults.count = medicosListFilter.size();
                }else{
                    String search = charSequence.toString().toLowerCase();
                    ArrayList<Medico> medicos = new ArrayList<>();

                    for (Medico medico: medicosListFilter) {
                        if(medico.getNome().toLowerCase().contains(search) || medico.getEspecialidade().toLowerCase().contains(search) ){
                            medicos.add(medico);
                        }
                    }

                    filterResults.values = medicos;
                    filterResults.count = medicos.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                medicosListFull = (ArrayList<Medico>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    //cria o layout de cada linha (card)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_medico_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    //exibe as insformações na linha (card)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = medicosListFull.get(position).getNome();
        String especialidade = medicosListFull.get(position).getEspecialidade();
        String crm = medicosListFull.get(position).getCrm();
        int id = medicosListFull.get(position).getId();

        holder.nome.setText(name);
        holder.especialidade.setText(especialidade);
        holder.crm.setText(crm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MedicoPerfil.class);

                intent.putExtra("id", id);
                intent.putExtra("nome", name);
                intent.putExtra("especialidade", especialidade);
                intent.putExtra("crm", crm);

                context.startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return medicosListFull.size();
    }


}
