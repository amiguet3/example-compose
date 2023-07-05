package com.example.myapplication.composables.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.view.PlanetsScreen
import com.example.myapplication.viewmodel.PlanetViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun TabsScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(0.dp)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colors.secondary
                )
            },
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = "")
                    },
                    text = {
                        Text(
                            text = item.title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen(navController)
        }
    }
}

@Composable
fun TabScreen(
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
        )
    }
}

data class TabRowItem(
    val title: String,
    val icon: ImageVector,
    val screen: @Composable (navController: NavController) -> Unit,
)

val tabRowItems = listOf(
    TabRowItem(
        title = "Planets",
        screen = { PlanetsScreen(null) },
        icon = Icons.Rounded.Place,
    ),
    TabRowItem(
        title = "Tab 2",
        screen = { TabScreen(text = "Tab 2 Content") },
        icon = Icons.Rounded.Search,
    ),
    TabRowItem(
        title = "Tab 3",
        screen = { TabScreen(text = "Tab 3 Content") },
        icon = Icons.Rounded.Star,
    )
)