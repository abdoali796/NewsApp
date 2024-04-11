package com.abdoali.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdoali.newsapp.mainScreen.MANIN_SCREEN
import com.abdoali.newsapp.mainScreen.mainScreen
import com.abdoali.newsapp.savedScreen.savedScreen
import com.abdoali.newsapp.ui.theme.NewsAppTheme
import com.abdoali.newsapp.uiCompound.NavigationScreens
import com.abdoali.newsapp.uiCompound.navigateSingleTopTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val navback by navController.currentBackStackEntryAsState()
                    val currencyScreens = navback?.destination?.route
                    val list = listOf(NavigationScreens.Main, NavigationScreens.Save)

                    Scaffold(bottomBar = {
                        NavigationBar {
                            list.forEach { navigationScreen ->
                                NavigationBarItem(selected = navigationScreen.route == currencyScreens,
                                    onClick = {
                                        navController.navigateSingleTopTo(navigationScreen.route)

                                    },
                                    icon = {
                                        Icon(
                                            imageVector = navigationScreen.icon,
                                            contentDescription = navigationScreen.route
                                        )
                                    },
                                    label = {
                                        Text(text = stringResource(id = navigationScreen.label))
                                    }

                                )
                            }
                        }
                    }) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = MANIN_SCREEN,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            mainScreen()
                            savedScreen()
                        }
                    }
                }
            }
        }
    }
}


