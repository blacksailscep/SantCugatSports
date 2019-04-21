package com.sancugat.cepsoft.santcugatsports.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sancugat.cepsoft.santcugatsports.API.Api;
import com.sancugat.cepsoft.santcugatsports.ApiService.EntidadService;
import com.sancugat.cepsoft.santcugatsports.EntidadManager;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;
import com.sancugat.cepsoft.santcugatsports.Login;
import com.sancugat.cepsoft.santcugatsports.MensajeError;
import com.sancugat.cepsoft.santcugatsports.R;
import com.sancugat.cepsoft.santcugatsports.RegistroActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Perfil.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private String pathPhoto;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Bitmap photo;
    private ImageView imageView;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        final EditText nombre = view.findViewById(R.id.edtNombre);
        final EditText correo = view.findViewById(R.id.edtCorreo);
        final EditText contrasenya = view.findViewById(R.id.edtContrasenya);
        final EditText temporada = view.findViewById(R.id.edtTemporada);
        final EditText direccion = view.findViewById(R.id.edtDireccion);
        final EditText nif = view.findViewById(R.id.edtNif);

        this.imageView = view.findViewById(R.id.imageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
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


        if(EntidadManager.getEntidad().getFoto()!=null) {
            Bitmap b = loadImageFromStorage(EntidadManager.getEntidad().getFoto());
            RoundedBitmapDrawable drawable = CreateRoundedImageBitmap(b);
            imageView.setImageDrawable(drawable);
        }

        nombre.setText(EntidadManager.getEntidad().getNombre());
        contrasenya.setText(EntidadManager.getEntidad().getContrasenya());
        correo.setText(EntidadManager.getEntidad().getCorreo());
        direccion.setText(EntidadManager.getEntidad().getDireccion());
        temporada.setText(EntidadManager.getEntidad().getTemporada()+"");
        nif.setText(EntidadManager.getEntidad().getNif());

        Button btoGuardar = view.findViewById(R.id.btoGuardar);
        btoGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), "Actualizando Entidad", Toast.LENGTH_SHORT).show();
                Entidad entidad = EntidadManager.getEntidad();
                entidad.setNombre(nombre.getText().toString());
                entidad.setContrasenya(contrasenya.getText().toString());
                entidad.setCorreo(correo.getText().toString());
                entidad.setDireccion(direccion.getText().toString());
                if(pathPhoto!=null)
                    entidad.setFoto(pathPhoto);
                entidad.setNif(nif.getText().toString());
                entidad.setTemporada(Integer.parseInt(temporada.getText().toString()));

                EntidadService entidadService = Api.getApi().create(EntidadService.class);
                Call<Entidad> entityCall = entidadService.updatetEntidad(entidad.getId(),entidad);
                entityCall.enqueue(new Callback<Entidad>() {
                    @Override
                    public void onResponse(Call<Entidad> call, Response<Entidad> response) {

                        Toast.makeText(getActivity().getApplicationContext(), "Entidad actualizada",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Entidad> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
            imageView.setImageDrawable(drawable);
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
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
