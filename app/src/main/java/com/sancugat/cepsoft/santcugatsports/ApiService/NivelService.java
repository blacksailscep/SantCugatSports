package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Nivel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NivelService {
    @GET("api/Nivels")
    Call<List<Nivel>> getNivel();

    @GET("api/Nivels/{id}")
    Call<Nivel> getNivelByID(@Path("id") int id);
}
