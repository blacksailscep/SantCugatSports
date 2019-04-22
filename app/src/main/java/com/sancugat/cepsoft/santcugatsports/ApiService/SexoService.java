package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Sexo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SexoService {
    @GET("api/Sexoes")
    Call<List<Sexo>> getSexo();

    @GET("api/Sexoes/{id}")
    Call<Sexo> getSexoByID(@Path("id") int id);
}
