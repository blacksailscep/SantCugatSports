package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Deportes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeporteService {
    @GET("api/Deportes")
    Call<List<Deportes>> getDeportes();

    @GET("api/Deportes/{id}")
    Call<Deportes> getDeportesByID(@Path("id") int id);
}
