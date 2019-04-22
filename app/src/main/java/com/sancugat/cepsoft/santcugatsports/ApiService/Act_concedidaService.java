package com.sancugat.cepsoft.santcugatsports.ApiService;

import com.sancugat.cepsoft.santcugatsports.Entities.Act_concedida;
import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Act_concedidaService
{
	@GET("api/Act_concedida")
	Call<List<Act_concedida>> getEntidades();

	@GET("api/Act_concedida/{id}")
	Call<Act_concedida> getEntityById(@Path("id") int id);

	@POST("api/Act_concedida")
	Call<Act_concedida> insertEntidad(@Body Entidad entidad);

	@DELETE("api/Act_concedida/{id}")
	Call<Act_concedida> deleteEntidad(@Path("id") int id);
}
