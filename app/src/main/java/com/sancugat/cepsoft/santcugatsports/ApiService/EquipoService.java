package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Equipo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EquipoService {

    @POST("api/Equipoes")
    Call<Equipo> insertEquipo(@Body Equipo equipo);
}
