package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EntidadService {
    @GET("api/Entidads")
    Call<List<Entidad>> getEntidades();

    @GET("api/Entidads/nombre/{nombre}/password/{password}")
    Call<Entidad> getEntityByUserAndPassword(@Path("nombre") String nombre, @Path("password")String password);

    @GET("api/Entidads/{id}")
    Call<Entidad> getEntityById(@Path("id") int id);

    @POST("api/Entidads")
    Call<Entidad> insertEntidad(@Body Entidad entidad);

    @DELETE("api/Entidads/{id}")
    Call<Entidad> deleteEntidad(@Path("id") int id);
}
