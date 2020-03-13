package com.example.pacifico.network;

import com.example.pacifico.model.foto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<foto>> getAllPhotos();
}