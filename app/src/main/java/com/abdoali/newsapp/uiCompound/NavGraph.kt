package com.abdoali.newsapp.uiCompound

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.abdoali.newsapp.R
import com.abdoali.newsapp.mainScreen.MANIN_SCREEN
import com.abdoali.newsapp.savedScreen.SAVED_SCREEN
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

sealed class NavigationScreens(
    val route: String, val icon: ImageVector, @StringRes val label: Int,
) {
    object Main : NavigationScreens(
        route = MANIN_SCREEN,
        icon = Icons.Default.Search,
        label = R.string.search
    )

    object Save : NavigationScreens(
        route = SAVED_SCREEN,
        icon = Icons.Default.Star,
        label = R.string.saved
    )

}



