package com.kevin.composestudy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun KevinNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(
            route = Home.route
        ) {
            HomeScreen()
        }
        composable(
            route = ListX.route
        ) {
            ListScreen()
        }
        composable(
            route = Me.route
        ) {
            MeScreen()
        }
    }
}