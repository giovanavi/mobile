package com.example.teste01.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teste01.R;
import com.example.teste01.model.Contato;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    public static ArrayList<Contato> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtViewNome;
        private final TextView txtViewTelefone;
        private final TextView txtViewEndereco;
        private final ImageView imageViewDelete;

        public ViewHolder(View view){
            super(view);
            txtViewNome = view.findViewById(R.id.txtViewNome);
            txtViewTelefone = view.findViewById(R.id.txtViewTelefone);
            txtViewEndereco = view.findViewById(R.id.txtViewEndereco);
            imageViewDelete = view.findViewById(R.id.imageViewDelete);
        }

        public TextView getTextViewNome(){
            return txtViewNome;
        }

        public TextView getTextViewTelefone(){
            return txtViewTelefone;
        }

        public TextView getTextViewEndereco(){
            return txtViewEndereco;
        }

        public ImageView getImageViewDelete(){
            return imageViewDelete;
        }

    }

    public CustomAdapter(ArrayList<Contato> data){
        this.dataSet = data;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.contato_layout, parent, false);

        return new ViewHolder(view);
    }

    public void onBindViewHolder( ViewHolder holder, int position) {
        Contato contato = dataSet.get(position);

        holder.getTextViewNome().setText(contato.getNome());
        holder.getTextViewTelefone().setText(contato.getTelefone());
        holder.getTextViewEndereco().setText(contato.getEndereco());

        holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.remove( holder.getAdapterPosition() );
                CustomAdapter.this.notifyDataSetChanged();
            }
        });

    }

    public int getItemCount() {
        return dataSet.size();
    }

}
