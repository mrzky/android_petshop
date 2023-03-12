package com.task.jetpackcomposeapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{petShopId}") {
        fun createRoute(petShopId: Int) = "home/$petShopId"
    }
}
