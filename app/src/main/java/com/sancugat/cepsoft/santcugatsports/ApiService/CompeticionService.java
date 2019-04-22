package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Competicion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompeticionService {
    @GET("api/Competicions")
    Call<List<Competicion>> getComp();

    @GET("api/Competicions/{id}")
    Call<Competicion> getCompByID(@Path("id") int id);
}
