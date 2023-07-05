package com.example.myapplication.repositories

import com.example.myapplication.service.ExamplePostData
import com.example.myapplication.service.Planet
import com.example.myapplication.service.RetrofitInstance
import retrofit2.Call

class PlanetRepository {
    private val planetService = RetrofitInstance.planetService

    suspend fun getPlanets(): List<Planet> {
        return planetService.getPlanets().results
    }

    suspend fun postData(): Call<ExamplePostData?>? {
        return planetService.postData(ExamplePostData(Planet()))
    }
}