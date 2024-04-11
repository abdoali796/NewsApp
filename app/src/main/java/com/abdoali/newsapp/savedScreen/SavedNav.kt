package com.abdoali.newsapp.savedScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SAVED_SCREEN = "SAVED_SCREEN"

fun NavGraphBuilder.savedScreen() {
    composable(SAVED_SCREEN) {
        SavedScreen()
    }
}
//
//fun NavController.NavToSaved(){
//    navigate(SAVED_SCREEN)
//}