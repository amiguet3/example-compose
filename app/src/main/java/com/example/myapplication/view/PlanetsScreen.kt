package com.example.myapplication.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.myapplication.viewmodel.PlanetViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.service.Planet
import kotlinx.coroutines.launch
import com.example.myapplication.viewmodel.AppViewModelProvider

@Composable
fun PlanetsScreen(
    navController: NavController?,
    viewModel: PlanetViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val planets by viewModel.planets.observeAsState(emptyList())
//    val planets by viewModel.planets.observe("")

    val nc = navController ?: rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchPlanets()
    }

    Column {
//        Text(text = "Planets", fontSize = 24.sp, modifier = Modifier.padding(all = 15.dp))
        Button(
            onClick = { coroutineScope.launch { viewModel.createItem() } },
//            enabled = itemUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create item")
        }
        if (planets.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp))
                LoadingSpinner()
            }
        } else {
            LazyColumn {
                items(planets) { planet ->
                    PlanetListItem(nc, planet)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun PlanetListItem(navController: NavController, planet: Planet) {
    Column(
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable {
                println("NAVIGATE ${"planets/${planet.name}"}")
                navController.navigate("planets/${planet.name}")
            },
    ) {
        Text(text = planet.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Diameter")
            Text(text = "${planet.diameter} m")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Population")
            Text(text = planet.population)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Gravity")
            Text(text = planet.gravity)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Terrain")
            Text(text = planet.terrain)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Day")
            Text(text = "${planet.rotation_period} h")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Year")
            Text(text = "${planet.orbital_period} d")
        }
    }
}

//@Composable
//fun InfinitelyPulsingHeart() {
//    // Creates an [InfiniteTransition] instance for managing child animations.
//    val infiniteTransition = rememberInfiniteTransition()
//
//    // Creates a child animation of float type as a part of the [InfiniteTransition].
//    val scale by infiniteTransition.animateFloat(
//        initialValue = 3f,
//        targetValue = 6f,
//        animationSpec = infiniteRepeatable(
//            // Infinitely repeating a 1000ms tween animation using default easing curve.
//            animation = tween(1000),
//            // After each iteration of the animation (i.e. every 1000ms), the animation will
//            // start again from the [initialValue] defined above.
//            // This is the default [RepeatMode]. See [RepeatMode.Reverse] below for an
//            // alternative.
//            repeatMode = RepeatMode.Restart
//        )
//    )
//
//    // Creates a Color animation as a part of the [InfiniteTransition].
//    val color by infiniteTransition.animateColor(
//        initialValue = Color.Red,
//        targetValue = Color(0xff800000), // Dark Red
//        animationSpec = infiniteRepeatable(
//            // Linearly interpolate between initialValue and targetValue every 1000ms.
//            animation = tween(1000, easing = LinearEasing),
//            // Once [TargetValue] is reached, starts the next iteration in reverse (i.e. from
//            // TargetValue to InitialValue). Then again from InitialValue to TargetValue. This
//            // [RepeatMode] ensures that the animation value is *always continuous*.
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//
//    Box(Modifier.fillMaxSize()) {
//        Icon(
//            Icons.Filled.Favorite,
//            contentDescription = null,
//            modifier = Modifier.align(Alignment.Center)
//                .graphicsLayer(
//                    scaleX = scale,
//                    scaleY = scale
//                ),
//            tint = color
//        )
//    }
//}




@Composable
fun LoadingSpinner(
    size: Dp = 32.dp, // indicator size
    sweepAngle: Float = 90f, // angle (lenght) of indicator arc
    color: androidx.compose.ui.graphics.Color = Color(0xFF35B2F3), // color of indicator arc line
    strokeWidth: Dp = 8.dp //width of cicle and ar lines
) {
    ////// animation //////

    // docs recomend use transition animation for infinite loops
    // https://developer.android.com/jetpack/compose/animation
    val transition = rememberInfiniteTransition()

    // define the changing value from 0 to 360.
    // This is the angle of the beginning of indicator arc
    // this value will change over time from 0 to 360 and repeat indefinitely.
    // it changes starting position of the indicator arc and the animation is obtained
    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        )
    )

    ////// draw /////

    // define stroke with given width and arc ends type considering device DPI
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    // draw on canvas
    Canvas(
        Modifier
            .progressSemantics() // (optional) for Accessibility services
            .size(size) // canvas size
            .padding(strokeWidth / 2) //padding. otherwise, not the whole circle will fit in the canvas
    ) {
        // draw "background" (gray) circle with defined stroke.
        // without explicit center and radius it fit canvas bounds
        drawCircle(Color.LightGray, style = stroke)

        // draw arc with the same stroke
        drawArc(
            color,
            // arc start angle
            // -90 shifts the start position towards the y-axis
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }
}