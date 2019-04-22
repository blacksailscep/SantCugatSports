package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Categoria_edad;
import com.sancugat.cepsoft.santcugatsports.Entities.Deportes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriaService {
    @GET("api/Categoria_edad")
    Call<List<Categoria_edad>> getCat();

    @GET("api/Categoria_edad/{id}")
    Call<Categoria_edad> getCatByID(@Path("id") int id);
}
