package com.example.myapplication.composables.navigation

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.R
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myapplication.composables.tabs.TabsScreen
import com.example.myapplication.view.PlanetDetail

// use your package
// import ...R
// import ....data.ScreensRoute
@Composable
fun NavHost(navController: NavHostController) {
//    val viewModel: PlanetViewModel by viewModels()

    NavHost(
        navController = navController,
        startDestination = ScreensRoute.PLANETS.name
    ) {
        composable(ScreensRoute.PLANETS.name) {
            TabsScreen(navController)
        }
        composable(
            route = "${ScreensRoute.PLANETS.name}/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            PlanetDetail(navController, backStackEntry.arguments?.getString("name")!!)
        }
        composable(ScreensRoute.SCREEN_2.name) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.screen_2), style = MaterialTheme.typography.headlineMedium)
            }
        }
        composable(ScreensRoute.SCREEN_3.name) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.screen_3), style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
