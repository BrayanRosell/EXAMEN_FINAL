package com.example.t4_final.Services;

import com.example.t4_final.Moduls.Libro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("N0036447/libros")
    Call<Libro> registrar(@Body Libro libro);

    @GET("N0036447/libros")
    Call<Libro> libro();
}
