package com.example.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TotemNet {

    @GET("totem/{cd_totem}")
    Call<Totem> obter(@Path(value = "cd_totem") Integer cd_totem);

    @GET("totem")
    Call<List<Totem>> obterTodos();

    @POST("totem")
    Call<Void> incluir(@Body Totem t);
}
