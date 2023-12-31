package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun PlanetDetail(navController: NavController, planetName: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Detail view for $planetName")
    }
}