package com.example.myapplication.service

data class PlanetsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Planet>
)