package com.example.demo;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LojaCliente {
	private final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/")
			.addConverterFactory(JacksonConverterFactory.create()).build();

	public PessoaNet getPessoaNet() {
		return retrofit.create(PessoaNet.class);
	}
	
}
