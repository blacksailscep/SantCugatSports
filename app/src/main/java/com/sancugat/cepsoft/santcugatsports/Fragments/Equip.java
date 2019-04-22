package com.sancugat.cepsoft.santcugatsports.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sancugat.cepsoft.santcugatsports.API.Api;
import com.sancugat.cepsoft.santcugatsports.ActividadesEquiposAdapter;
import com.sancugat.cepsoft.santcugatsports.ApiService.CategoriaService;
import com.sancugat.cepsoft.santcugatsports.ApiService.CompeticionService;
import com.sancugat.cepsoft.santcugatsports.ApiService.DeporteService;
import com.sancugat.cepsoft.santcugatsports.ApiService.NivelService;
import com.sancugat.cepsoft.santcugatsports.ApiService.SexoService;
import com.sancugat.cepsoft.santcugatsports.EntidadManager;
import com.sancugat.cepsoft.santcugatsports.Entities.Categoria_edad;
import com.sancugat.cepsoft.santcugatsports.Entities.Competicion;
import com.sancugat.cepsoft.santcugatsports.Entities.Deportes;
import com.sancugat.cepsoft.santcugatsports.Entities.Equipo;
import com.sancugat.cepsoft.santcugatsports.Entities.Nivel;
import com.sancugat.cepsoft.santcugatsports.Entities.Sexo;
import com.sancugat.cepsoft.santcugatsports.MensajeError;
import com.sancugat.cepsoft.santcugatsports.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Equip extends Fragment {

    private ListView listAct;
    private Equipo equipo;
    private TextView nombre;
    private TextView deporte;
    private TextView cat;
    private TextView com;
    private TextView sex;
    private TextView niv;
    private ImageView foto;
    private Button buttonAceptar;
    private Button buttonCancelar;
    private Deportes dep ;
    private Competicion comp;
    private Categoria_edad categoria;
    private Nivel nivel;
    private Sexo sexo;
    public Equip() {
        // Required empty public constructor
    }

    //Recibe un Equipo.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_equip, container, false);
        nombre = (TextView) view.findViewById(R.id.txtNombreEquipo);
        deporte = (TextView) view.findViewById(R.id.txtDeporte);
        cat = (TextView) view.findViewById(R.id.txtCatEdat);
        com = (TextView) view.findViewById(R.id.txtCompeticion);
        niv = (TextView) view.findViewById(R.id.txtNivel);
        sex = (TextView) view.findViewById(R.id.txtSexo);
        foto = (ImageView) view.findViewById(R.id.imageViewEquipo);
        buttonAceptar = (Button) view.findViewById(R.id.aceptar);
        buttonCancelar = (Button) view.findViewById(R.id.cancelar);
        listAct = (ListView) view.findViewById(R.id.listActividades);
        if(EntidadManager.getEntidad().getFoto()!=null) {
            Bitmap b = loadImageFromStorage(equipo.getFoto());
            RoundedBitmapDrawable drawable = CreateRoundedImageBitmap(b);
            foto.setImageDrawable(drawable);
        }

        nombre.setText(equipo.getNombre());
        deporte.setText(dep.getNombre());
        cat.setText(categoria.getNombre());
        com.setText(comp.getNombre());
        niv.setText(nivel.getNombre());
        sex.setText(sexo.getNombre());
        ActividadesEquiposAdapter adapter = new ActividadesEquiposAdapter(this.getContext(), R.layout.actividades_list, equipo.getLstAct_Concedidas());
        listAct.setAdapter(adapter);

        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abre una actividad y le pasamos una actividad.
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vuelve a la pantalla anterior.
            }
        });
        return view;
    }
    private void cargarDatos(){
        DeporteService deporteService = Api.getApi().create(DeporteService.class);
        Call<Deportes> deporteCall = deporteService.getDeportesByID(equipo.getId_sport());

        deporteCall.enqueue(new Callback<Deportes>() {
            @Override
            public void onResponse(Call<Deportes> call, Response<Deportes> response) {
                switch (response.code()) {
                    case 200:
                        dep = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Deportes> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        CompeticionService competicionService = Api.getApi().create(CompeticionService.class);
        Call<Competicion> competicionCall = competicionService.getCompByID(equipo.getId_competicion());

        competicionCall.enqueue(new Callback<Competicion>() {
            @Override
            public void onResponse(Call<Competicion> call, Response<Competicion> response) {
                switch (response.code()) {
                    case 200:
                        comp = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Competicion> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        CategoriaService catService = Api.getApi().create(CategoriaService.class);
        Call<Categoria_edad> catCall = catService.getCatByID(equipo.getId_categoria_edad());

        catCall.enqueue(new Callback<Categoria_edad>() {
            @Override
            public void onResponse(Call<Categoria_edad> call, Response<Categoria_edad> response) {
                switch (response.code()) {
                    case 200:
                        categoria = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Categoria_edad> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        NivelService nivService = Api.getApi().create(NivelService.class);
        Call<Nivel> nivCall = nivService.getNivelByID(equipo.getId_nivel());

        nivCall.enqueue(new Callback<Nivel>() {
            @Override
            public void onResponse(Call<Nivel> call, Response<Nivel> response) {
                switch (response.code()) {
                    case 200:
                        nivel = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Nivel> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        SexoService sexService = Api.getApi().create(SexoService.class);
        Call<Sexo> sexCall = sexService.getSexoByID(equipo.getId_sexo());

        sexCall.enqueue(new Callback<Sexo>() {
            @Override
            public void onResponse(Call<Sexo> call, Response<Sexo> response) {
                switch (response.code()) {
                    case 200:
                        sexo = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Sexo> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap b=null;
        try {
            File f=new File(path, "profile.jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;

    }
    private RoundedBitmapDrawable CreateRoundedImageBitmap(Bitmap bitmap)
    {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);

        return roundedBitmapDrawable;
    }
}
