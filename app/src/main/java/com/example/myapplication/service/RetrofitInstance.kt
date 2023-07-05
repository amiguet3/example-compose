package com.example.myapplication.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//    private const val BASE_URL = "https://swapi.dev/api/"
    private const val BASE_URL = "http://192.168.1.50:5010/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val planetService: PlanetService by lazy {
        retrofit.create(PlanetService::class.java)
    }
}