package com.example.weather.screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(route = Screen.ListScreen.route) {
            ListScreen(navController, context)
        }

        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController, context)
        }

        composable(
            route = Screen.DetailsScreen.route + "/{city_name}",
            arguments = listOf(
                navArgument("city_name") {
                    type = NavType.StringType
                    defaultValue = "default"
                }
            )
        ) { entry ->
            DetailsScreen(cityName = entry.arguments?.getString("city_name"))
        }
    }
}