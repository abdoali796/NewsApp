package com.abdoali.newsapp.mainScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MANIN_SCREEN = "MANIN_SCREEN"

fun NavGraphBuilder.mainScreen() {
    composable(MANIN_SCREEN) {
        MainScreen()
    }

}
//
//fun NavController.NavToMain(){
//    navigate(MANIN_SCREEN)
//}