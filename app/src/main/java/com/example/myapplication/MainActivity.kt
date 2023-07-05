package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composables.navigation.DrawerBody
import com.example.myapplication.composables.navigation.NavHost
import com.example.myapplication.composables.navigation.TopBar
import com.example.myapplication.composables.navigation.drawerScreens
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.PlanetViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: PlanetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            PlanetsScreen(viewModel)
//        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DrawerNavigationScreen()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerNavigationScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                titleResId = R.string.app_name,
                openDrawer =
                {
                    scope.launch {
                        // Open the drawer with animation and suspend until it is fully opened or animation has been canceled
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = true,
        drawerContent = {
            DrawerBody(
                menuItems = drawerScreens,
                scaffoldState,
                scope
            ) {
                println("NAVIGATION it.id.name ${it.id.name}")
                navController.navigate(it.id.name) {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                }
            }
        }
    ) {
        NavHost(navController = navController)
    }
}