package com.example.teste01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.teste01.model.Contato;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<Contato> agenda;

    public ExpandableListAdapter(Context context, List<Contato> agenda) {
        this.context = context;
        this.agenda = agenda;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        //desenha na tela uma view
        Contato contato = (Contato) getGroup(i);

        if(view == null){
            //vai inflar o elemento de layout list_group

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }
        return null;
    }

    @Override
    public Object getGroup(int i) {
        return agenda.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }
}
