package com.sancugat.cepsoft.santcugatsports;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sancugat.cepsoft.santcugatsports.API.Api;
import com.sancugat.cepsoft.santcugatsports.ApiService.EntidadService;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Bitmap photo;
    private String pathPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        final EditText nombre = findViewById(R.id.edtNombre);
        final EditText correo = findViewById(R.id.edtCorreo);
        final EditText contrasenya = findViewById(R.id.edtContrasenya);
        final EditText temporada = findViewById(R.id.edtTemporada);
        final EditText direccion = findViewById(R.id.edtDireccion);
        final EditText nif = findViewById(R.id.edtNif);

        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        Button btoResgistro = findViewById(R.id.btoRegistrarse);
        btoResgistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nombre.getText().toString().isEmpty()
                        || !correo.getText().toString().isEmpty()
                        || !contrasenya.getText().toString().isEmpty()
                        || !temporada.getText().toString().isEmpty()
                        || !direccion.getText().toString().isEmpty()
                        || !nif.getText().toString().isEmpty() ) {

                    Entidad entidad = new Entidad();

                    entidad.setNombre(nombre.getText().toString());
                    entidad.setContrasenya(contrasenya.getText().toString());
                    entidad.setCorreo(correo.getText().toString());
                    entidad.setDireccion(direccion.getText().toString());
                    entidad.setFoto(pathPhoto);
                    entidad.setNif(nif.getText().toString());
                    entidad.setTemporada(Integer.parseInt(temporada.getText().toString()));

                    EntidadService entidadService = Api.getApi().create(EntidadService.class);
                    Call<Entidad> entityCall = entidadService.insertEntidad(entidad);
                    entityCall.enqueue(new Callback<Entidad>() {
                        @Override
                        public void onResponse(Call<Entidad> call, Response<Entidad> response) {
                            switch (response.code()) {
                                case 201:
                                    Entidad entidad = response.body();
                                    startActivity(new Intent(RegistroActivity.this,Login.class));
                                    EntidadManager.setEntidad(entidad);
                                    break;
                                case 400:
                                    Gson gson= new Gson();
                                    MensajeError mensajeError = gson.fromJson(response.errorBody().charStream(),MensajeError.class);
                                    Toast.makeText(getApplicationContext(),mensajeError.getMensaje(), Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    Gson gson2= new Gson();
                                    MensajeError mensajeError2 = gson2.fromJson(response.errorBody().charStream(),MensajeError.class);
                                    Toast.makeText(getApplicationContext(),mensajeError2.getMensaje(), Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                        @Override
                        public void onFailure(Call<Entidad> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    Toast.makeText(RegistroActivity.this.getApplication(),
                            "Faltan rellenar datos", Toast.LENGTH_LONG).show();
                }

            }
        });
        //https://www.leon7dias.com/wp-content/uploads/2018/06/club-deportivo.png
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            pathPhoto = saveToInternalStorage(photo);
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
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
}
