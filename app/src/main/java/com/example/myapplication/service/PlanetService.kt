package com.example.myapplication.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlanetService {
    @GET("planets")
    suspend fun getPlanets(): PlanetsResponse

    // and along with that we are passing a parameter as users
    @POST(".")
    fun postData(@Body dataModel: ExamplePostData?): Call<ExamplePostData?>?
}

data class ExamplePostData(
    val item: Planet
)