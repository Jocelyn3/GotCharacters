package com.example.weather.screen

sealed class Screen(val route: String) {
    object ListScreen: Screen("list_screen")
    object SearchScreen: Screen("search_screen")
    object DetailsScreen: Screen("details_screen")

}
