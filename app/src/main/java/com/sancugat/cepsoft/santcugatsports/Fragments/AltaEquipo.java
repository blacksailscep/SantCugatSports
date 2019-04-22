package com.sancugat.cepsoft.santcugatsports.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sancugat.cepsoft.santcugatsports.API.Api;
import com.sancugat.cepsoft.santcugatsports.ApiService.CategoriaService;
import com.sancugat.cepsoft.santcugatsports.ApiService.CompeticionService;
import com.sancugat.cepsoft.santcugatsports.ApiService.DeporteService;
import com.sancugat.cepsoft.santcugatsports.ApiService.EquipoService;
import com.sancugat.cepsoft.santcugatsports.ApiService.NivelService;
import com.sancugat.cepsoft.santcugatsports.ApiService.SexoService;
import com.sancugat.cepsoft.santcugatsports.EntidadManager;
import com.sancugat.cepsoft.santcugatsports.Entities.Categoria_edad;
import com.sancugat.cepsoft.santcugatsports.Entities.Competicion;
import com.sancugat.cepsoft.santcugatsports.Entities.Deportes;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;
import com.sancugat.cepsoft.santcugatsports.Entities.Equipo;
import com.sancugat.cepsoft.santcugatsports.Entities.Nivel;
import com.sancugat.cepsoft.santcugatsports.Entities.Sexo;
import com.sancugat.cepsoft.santcugatsports.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AltaEquipo extends Fragment {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private String pathPhoto;
    private Bitmap photo;
    private Equipo equipos;
    private Spinner spinnerDeporte;
    private Spinner spinnerCom;
    private Spinner spinnerCat;
    private Spinner spinnerNiv;
    private Spinner spinnerSex;
    private Button buttonAceptar;
    private EditText textNombre;
    private ImageView image;
    private List<Deportes> dep;
    private List<Competicion> comp;
    private List<Categoria_edad> cat;
    private List<Nivel> niv;
    private List<Sexo> sexo;


    public AltaEquipo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        spinnerDeporte = (Spinner) view.findViewById(R.id.spinDeporte);
        spinnerCom = (Spinner) view.findViewById(R.id.spinComp);
        spinnerCat = (Spinner) view.findViewById(R.id.spinCat);
        spinnerNiv = (Spinner) view.findViewById(R.id.spinNiv);
        spinnerSex = (Spinner) view.findViewById(R.id.spinSex);
        buttonAceptar = (Button) view.findViewById(R.id.btoaceptar);
        textNombre = (EditText) view.findViewById(R.id.edtNombreEquip);
        image = (ImageView) view.findViewById(R.id.imageViewEquipoz);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), "Actualizando Entidad", Toast.LENGTH_SHORT).show();

                List<Equipo> equips = EntidadManager.getEntidad().getLstEquipos();
                Deportes dep = (Deportes) spinnerDeporte.getSelectedItem();
                Competicion comp = (Competicion) spinnerCom.getSelectedItem();
                Categoria_edad cat = (Categoria_edad) spinnerCat.getSelectedItem();
                Nivel niv = (Nivel) spinnerNiv.getSelectedItem();
                Sexo se = (Sexo) spinnerSex.getSelectedItem();
                if(pathPhoto!=null)
                    equipos.setFoto(pathPhoto);
                equipos.setId(equips.size() + 1);
                equipos.setNombre(textNombre.getText().toString());
                equipos.setId_sport(dep.getId());
                equipos.setId_competicion(comp.getId());
                equipos.setId_categoria_edad(cat.getId());
                equipos.setId_nivel(niv.getId());
                equipos.setId_sexo(se.getId());

                EquipoService equipoService = Api.getApi().create(EquipoService.class);
                Call<Equipo> entityCall = equipoService.insertEquipo(equipos);
                entityCall.enqueue(new Callback<Equipo>() {
                    @Override
                    public void onResponse(Call<Equipo> call, Response<Equipo> response) {

                        Toast.makeText(getActivity().getApplicationContext(), "Equipo añadido",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Equipo> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }


    public void cargarSpiners(){


        ArrayAdapter<Deportes> adapterDep = new ArrayAdapter<Deportes>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, dep);
        ArrayAdapter<Competicion> adapterCom = new ArrayAdapter<Competicion>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, comp);
        ArrayAdapter<Categoria_edad> adapterCat = new ArrayAdapter<Categoria_edad>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, cat);
        ArrayAdapter<Nivel> adapterNiv = new ArrayAdapter<Nivel>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, niv);
        ArrayAdapter<Sexo> adapterSex = new ArrayAdapter<Sexo>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, sexo);

        spinnerDeporte.setAdapter(adapterDep);
        spinnerCom.setAdapter(adapterCom);
        spinnerCat.setAdapter(adapterCat);
        spinnerNiv.setAdapter(adapterNiv);
        spinnerSex.setAdapter(adapterSex);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private RoundedBitmapDrawable CreateRoundedImageBitmap(Bitmap bitmap)
    {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);

        return roundedBitmapDrawable;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");

            RoundedBitmapDrawable drawable = CreateRoundedImageBitmap(photo);
            image.setImageDrawable(drawable);
            pathPhoto = saveToInternalStorage(photo);
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void cargarDatos(){
        DeporteService deporteService = Api.getApi().create(DeporteService.class);
        Call<List<Deportes>> deporteCall = deporteService.getDeportes();

        deporteCall.enqueue(new Callback<List<Deportes>>() {
            @Override
            public void onResponse(Call<List<Deportes>> call, Response<List<Deportes>> response) {
                switch (response.code()) {
                    case 200:
                        dep = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Deportes>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        CompeticionService competicionService = Api.getApi().create(CompeticionService.class);
        Call<List<Competicion>> competicionCall = competicionService.getComp();

        competicionCall.enqueue(new Callback<List<Competicion>>() {
            @Override
            public void onResponse(Call<List<Competicion>> call, Response<List<Competicion>> response) {
                switch (response.code()) {
                    case 200:
                        comp = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Competicion>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        CategoriaService catService = Api.getApi().create(CategoriaService.class);
        Call<List<Categoria_edad>> catCall = catService.getCat();

        catCall.enqueue(new Callback<List<Categoria_edad>>() {
            @Override
            public void onResponse(Call<List<Categoria_edad>> call, Response<List<Categoria_edad>> response) {
                switch (response.code()) {
                    case 200:
                        cat = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Categoria_edad>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        NivelService nivService = Api.getApi().create(NivelService.class);
        Call<List<Nivel>> nivCall = nivService.getNivel();

        nivCall.enqueue(new Callback<List<Nivel>>() {
            @Override
            public void onResponse(Call<List<Nivel>> call, Response<List<Nivel>> response) {
                switch (response.code()) {
                    case 200:
                        niv = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Nivel>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        SexoService sexService = Api.getApi().create(SexoService.class);
        Call<List<Sexo>> sexCall = sexService.getSexo();

        sexCall.enqueue(new Callback<List<Sexo>>() {
            @Override
            public void onResponse(Call<List<Sexo>> call, Response<List<Sexo>> response) {
                switch (response.code()) {
                    case 200:
                        sexo = response.body();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Sexo>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
