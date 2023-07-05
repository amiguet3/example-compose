package com.example.myapplication.composables.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    titleResId: Int,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = titleResId))
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        openDrawer()
                    },
                contentDescription = null,
//                tint = Color.White
            )
        },
//        modifier = Modifier.background(Color.White)
    )
}