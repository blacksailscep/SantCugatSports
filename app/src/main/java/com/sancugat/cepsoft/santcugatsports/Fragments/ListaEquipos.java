package com.sancugat.cepsoft.santcugatsports.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sancugat.cepsoft.santcugatsports.EntidadManager;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;
import com.sancugat.cepsoft.santcugatsports.Entities.Equipo;
import com.sancugat.cepsoft.santcugatsports.EquiposAdapter;
import com.sancugat.cepsoft.santcugatsports.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaEquipos extends Fragment {

    Entidad enti;
    RecyclerView recyclerView;
    private FloatingActionButton butonAdd;

    public static Fragment newIntance(){
        return new ListaEquipos();
    }

    public ListaEquipos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_equipos, container, false);


        enti = EntidadManager.getEntidad();
        List<Equipo> equips= enti.getLstEquipos();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        butonAdd = (FloatingActionButton) view.findViewById(R.id.buttonAdd);


        //recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        EquiposAdapter adapter = new EquiposAdapter(this.getContext(), equips);
        recyclerView.setAdapter(adapter);

        butonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abre el fracment Alta Equipo
            }
        });
        return view;
    }

}
