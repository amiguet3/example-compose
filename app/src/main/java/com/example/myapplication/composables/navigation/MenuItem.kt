package com.example.myapplication.composables.navigation

import com.example.myapplication.R

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.PLANETS, R.string.planets_screen),
    MenuItem(ScreensRoute.SCREEN_2, R.string.screen_2),
    MenuItem(ScreensRoute.SCREEN_3, R.string.screen_3),
)

enum class ScreensRoute {
    PLANETS, SCREEN_2, SCREEN_3
}