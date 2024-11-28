package com.example.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BicicletaNet {
    @GET("bicicleta/{cd_bicicleta}")
    Call<Bicicleta> obter(@Path(value = "cd_bicicleta") String cd_bicicleta);
    
    @GET("bicicleta")
    Call<List<Bicicleta>> obterTodos();
    
    @POST("bicicleta")
    Call<Void> incluir(@Body Bicicleta b);
}
