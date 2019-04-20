package com.sancugat.cepsoft.santcugatsports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sancugat.cepsoft.santcugatsports.API.Api;
import com.sancugat.cepsoft.santcugatsports.ApiService.EntidadService;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button bto_registro = findViewById(R.id.bto_registro);
        bto_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,RegistroActivity.class));
            }
        });

        Button bto_Loguin = findViewById(R.id.bto_login);
        bto_Loguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText user_text = findViewById(R.id.edt_user);
                EditText passwor = findViewById(R.id.edt_password);

                Entidad entidad = new Entidad();
                entidad.setNombre(user_text.getText().toString());
                entidad.setContrasenya(passwor.getText().toString());

                EntidadService entidadService = Api.getApi().create(EntidadService.class);
                Call<Entidad> entityCall = entidadService.getEntityByUserAndPassword
                        (user_text.getText().toString(),passwor.getText().toString());

                entityCall.enqueue(new Callback<Entidad>() {
                    @Override
                    public void onResponse(Call<Entidad> call, Response<Entidad> response) {
                        switch (response.code()) {
                            case 200:
                                Entidad entidad = response.body();
                                EntidadManager.setEntidad(entidad);
                                startActivity(new Intent(Login.this,MainActivity.class));
                                break;
                            case 400:
                                Gson gson= new Gson();
                                MensajeError mensajeError = gson.fromJson(response.errorBody().charStream(),MensajeError.class);
                                Toast.makeText(getApplicationContext(),mensajeError.getMensaje(), Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Entidad> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getCause() + " . " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
