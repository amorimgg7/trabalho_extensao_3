package com.example.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PessoaNet {

    @GET("pessoa/{cd_pessoa}")
    Call<Pessoa> obter(@Path(value = "cd_pessoa") Integer cd_pessoa);

    @GET("login/{ds_nome}/{ds_senha}")
    Call<Pessoa> login(@Path(value = "ds_nome") String ds_nome, @Path(value = "ds_senha") String ds_senha);

    @GET("pessoa")
    Call<List<Pessoa>> obterTodos();

    @POST("pessoa")
    Call<Void> incluir(@Body Pessoa p);

}
