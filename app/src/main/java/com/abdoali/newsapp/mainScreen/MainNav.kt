package com.abdoali.newsapp.mainScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlin.jvm.internal.MagicApiIntrinsics

const val MANIN_SCREEN = "MANIN_SCREEN"

fun NavGraphBuilder.mainScreen() {
    composable(MANIN_SCREEN) {
        MainScreen()
    }

}

fun NavController.NavToMain(){
    navigate(MANIN_SCREEN)
}