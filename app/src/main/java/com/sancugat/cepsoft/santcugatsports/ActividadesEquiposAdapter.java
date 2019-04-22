package com.sancugat.cepsoft.santcugatsports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sancugat.cepsoft.santcugatsports.Entities.Act_concedida;

import java.util.ArrayList;
import java.util.List;

public class ActividadesEquiposAdapter extends ArrayAdapter<ArrayList<Act_concedida>> {
    private  Context context;
    private  ArrayList<Act_concedida> lstAct_Concedidas;

    public ActividadesEquiposAdapter(Context context, int resource, ArrayList<Act_concedida> lstAct_Concedidas) {
        super(context, resource);
        this.context = context;
        this.lstAct_Concedidas = lstAct_Concedidas;
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.actividades_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.ActNombre);
        textView.setText(lstAct_Concedidas.get(position).getNombre());

        return rowView;
    }
    */
}

