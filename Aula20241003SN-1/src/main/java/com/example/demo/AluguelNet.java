package com.example.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AluguelNet {

    @GET("aluguel/{cd_pessoa}/{pago}")
    Call<Aluguel> obter(@Path(value = "cd_pessoa") Integer cd_pessoa, @Path(value = "pago") Boolean pago);

    @GET("aluguel")
    Call<List<Aluguel>> obterTodos();

    @POST("aluguel")
    Call<Void> incluir(@Body Aluguel a);

    @PUT("aluguel")
    Call<Void> atualizar(@Body Aluguel a);

}
